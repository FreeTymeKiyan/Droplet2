package org.dianlava.droplet.Activities;

import org.dianlava.droplet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * µÇÂ¼Ò³Ãæ
 * 
 * @author FreeTymeKiyan
 * @date 2012-07-11
 * @version 1.0 
 * */
public class LoginActivity extends Activity {
	
	RelativeLayout layout;
	View loginView;
	
	RelativeLayout weibo_auth_layout;
	RelativeLayout renren_auth_layout;
	RelativeLayout douban_auth_layout;
	
	int screenwidth, screenheight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;
		
		loginView = this.getLayoutInflater().inflate(R.layout.login_layout, null);
		layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) (screenwidth * 0.8), (int) (screenheight * 0.7));
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout.addView(loginView, lp);
		
		setContentView(layout);
		
		weibo_auth_layout = (RelativeLayout) findViewById(R.id.weibo_auth);
		renren_auth_layout = (RelativeLayout) findViewById(R.id.renren_auth);
		douban_auth_layout = (RelativeLayout) findViewById(R.id.douban_auth);
		weibo_auth_layout.setOnClickListener(clickListener);
		renren_auth_layout.setOnClickListener(clickListener);
		douban_auth_layout.setOnClickListener(clickListener);
	}
	
		OnClickListener clickListener = new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				authorize(arg0.getId());
			}
		};
	
	private void authorize(int id_flag){
		Intent intent = new Intent(LoginActivity.this, AuthorizeActivity.class);
		intent.addFlags(id_flag);
		startActivity(intent);
		finish();
	}
	
}
