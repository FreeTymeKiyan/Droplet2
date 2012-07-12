package org.dianlava.droplet.Activities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dianlava.droplet.R;
import org.dianlava.droplet.Utils.ConstantUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * 授权认证页面
 * 
 * @author FreeTymeKiyan
 * @date 2012-07-11
 * @version 1.0 
 * */
public class AuthorizeActivity extends Activity {

	WebView web_view;
	ProgressBar progress_bar;
	
	int which_id;
	/**记录认证信息的偏好设置库*/
	private SharedPreferences auth_info;
	
	//豆瓣认证用到的中间变量
	String request_token = "";
	String request_token_secret = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		which_id = intent.getFlags();
		
		setContentView(R.layout.auth_layout);
		auth_info = getSharedPreferences(ConstantUtil.AUTHORIZATION_INFO, MODE_WORLD_READABLE);
		
		progress_bar = (ProgressBar) findViewById(R.id.auth_progress);
		
		web_view = (WebView) findViewById(R.id.auth_web);
		WebSettings settings = web_view.getSettings();  
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        web_view.requestFocusFromTouch();
		openBrowser();
	}
	
	/**
	 * 打开认证授权页面
	 * 还有与各个平台相关的一些方法
	 * */
	private void openBrowser(){
		switch(which_id){
		case R.id.weibo_auth:
			weiboAuth();
			break;
		case R.id.renren_auth:
			renrenAuth();
			break;
		case R.id.douban_auth:
			doubanAuth();
			break;
		default:
			break;
		}
	}
	
	private void renrenAuth(){
		String url = ConstantUtil.RENREN_AUTH_URL + URLEncoder.encode(ConstantUtil.RENREN_PERMISSIONS);
		web_view.loadUrl(url);
		web_view.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				progress_bar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
				if(url.contains("access_token")){
					String access_token = url.substring(url.indexOf("=" + 1), url.indexOf("&"));
					access_token = URLDecoder.decode(access_token);
					String expires_in = url.substring(url.indexOf("&") + 12, url.indexOf("&scope"));
					long expires_time = Long.parseLong(expires_in) + System.currentTimeMillis();
					auth_info.edit().putString(ConstantUtil.RENREN_ACCESS_TOKEN, access_token).commit();
					auth_info.edit().putLong(ConstantUtil.RENREN_EXPIRE_TIME, expires_time).commit();
					nextActivity(true);
				}else if(url.contains("login_denied")){
					nextActivity(false);
				}
			}
		});
	}
	
	private void weiboAuth(){
		web_view.loadUrl(ConstantUtil.WEIBO_AUTH_URL);
		web_view.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				progress_bar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
				if(url.contains("access_token")){
					String access_token = url.substring(url.indexOf("=" + 1), url.indexOf("&"));
//					access_token = URLDecoder.decode(access_token);
					String expires_in = url.substring(url.indexOf("&expires_in") + 12, url.indexOf("&uid"));
					long expires_time = Long.parseLong(expires_in) + System.currentTimeMillis();
					String uid = url.substring(url.indexOf("uid=") + 4);
					auth_info.edit().putString(ConstantUtil.WEIBO_ACCESS_TOKEN, access_token).commit();
					auth_info.edit().putLong(ConstantUtil.WEIBO_EXPIRE_TIME, expires_time).commit();
					auth_info.edit().putString(ConstantUtil.WEIBO_UID, uid).commit();
					nextActivity(true);
				}else if(url.contains("access_denied")){
					nextActivity(false);
				}
			}
		});
	}
	
	private void doubanAuth(){
		String result = doubanHttpAuth(1, request_token, request_token_secret);
		if(result != null){
			request_token_secret = result.substring(result.indexOf("=")+1, result.indexOf("&"));
			request_token = result.substring(result.lastIndexOf("=")+1, result.length());
		}else
			nextActivity(false);
		String url2 = ConstantUtil.DOUBAN_AUTH_URL2 + "?oauth_token=" + request_token + "&oauth_callback = www.dian.org.cn";
		web_view.loadUrl(url2);
		web_view.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				progress_bar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
				if(url.contains("apptokens")){
					String tempStr = doubanHttpAuth(3, request_token, request_token_secret);
					if(tempStr != null){
						String access_token_secret = tempStr.substring(tempStr.indexOf("=")+1, tempStr.indexOf("&"));
						String access_token = tempStr.substring(tempStr.indexOf("&")+13, tempStr.lastIndexOf("&"));
						String uid = tempStr.substring(tempStr.lastIndexOf("=")+1, tempStr.length());
						auth_info.edit().putString(ConstantUtil.DOUBAN_ACCESS_TOKEN, access_token).commit();
						auth_info.edit().putString(ConstantUtil.DOUBAN_ACCESS_TOKEN_SECRET, access_token_secret).commit();
						auth_info.edit().putString(ConstantUtil.DOUBAN_UID, uid).commit();
						nextActivity(true);
					}else{
						nextActivity(false);
					}
				}
			}
		});
	}
	
	private String doubanHttpAuth(int step, String requestToken, String requestTokenSecret){
		//第一个为requestToken，第二个为requestTokenSecret
		String result = "";
		String url;
		if(step == 1){
			 url = ConstantUtil.DOUBAN_AUTH_URL1;
		}else if(step == 3){
			url = ConstantUtil.DOUBAN_AUTH_URL3;
		}else{
			return null;
		}
		HttpGet request = new HttpGet(url);
		// 获取时间戳
		String timeStamp = (System.currentTimeMillis() / 1000) + "";
		// 生成单次值
		String nonce = System.currentTimeMillis() + "";
		// 利用OAuth库进行签名生成oauth_signature
		OAuthConsumer consumer = new OAuthConsumer("", ConstantUtil.DOUBAN_API_KEY, 
				ConstantUtil.DOUBAN_API_KEY_SECRET,null);
		OAuthAccessor accessor = new OAuthAccessor(consumer);
		accessor.accessToken = "";
		accessor.requestToken = requestToken;
		accessor.tokenSecret = requestTokenSecret;
		OAuthMessage message = new OAuthMessage("GET", url, null);
		message.addParameter("oauth_consumer_key", ConstantUtil.DOUBAN_API_KEY);
		message.addParameter("oauth_signature_method", "HMAC-SHA1");
		message.addParameter("oauth_timestamp", timeStamp);
		message.addParameter("oauth_nonce", nonce);
		message.addParameter("oauth_version", "1.0");
		if(step == 3)
			message.addParameter("oauth_token", requestToken);
		try {
			message.sign(accessor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 我们自己生成Authorization头部参数，只是利用OAuth库帮我们生成oauth_signature
			String tempStr = "OAuth ";
			tempStr = tempStr + "realm=" + "" + ",";
			tempStr = tempStr + "oauth_consumer_key=" + ConstantUtil.DOUBAN_API_KEY + ",";
			tempStr = tempStr + "oauth_signature_method=" + "HMAC-SHA1" + ",";
			tempStr = tempStr + "oauth_signature=" + message.getParameter("oauth_signature") + ",";
			tempStr = tempStr + "oauth_timestamp=" + timeStamp + ",";
			tempStr = tempStr + "oauth_nonce=" + nonce + ",";
			tempStr = tempStr + "oauth_version=" + "1.0";
			if(step == 3){
				tempStr = tempStr + "oauth_token=" + requestToken + ",";
			}
			// 添加Authorization头部参数
			request.addHeader("Authorization", tempStr);
			/* 发送请求 */
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response;
			response = client.execute(request);
			/* 检查返回值是否有效 */
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != 200){
				Log.d("statuscode", "200error");
				return null;
			}
			result = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private void nextActivity(boolean isLogin){
		Intent intent = new Intent();
		if(isLogin){
			intent.setClass(AuthorizeActivity.this, MyMainActivity.class);
		}else{
			intent.setClass(AuthorizeActivity.this, LoginActivity.class);
		}
		startActivity(intent);
		finish();
	}

}
