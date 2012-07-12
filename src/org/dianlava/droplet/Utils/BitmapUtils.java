package org.dianlava.droplet.Utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

/**
 * 图片相关的工具方法类
 * */
public class BitmapUtils {
	
	/**数据库限制的图片大小*/
	private static int REQUERED_SIZE = 65000;
	
	/**
	 * 图片压缩并将Bitmap保存到文件中
	 * 
	 * @param Bitmap
	 * */
	public byte[] saveBitmap(Bitmap beforeCompress) throws IOException {
		/*得到图片的大小*/
		Config config = beforeCompress.getConfig();
		int factor = 0;
		if(config.compareTo(Config.ALPHA_8) == 0) {
			factor = 1;
		} else if(config.compareTo(Config.ARGB_4444) == 0) {
			factor = 2;
		} else if (config.compareTo(Config.ARGB_8888) == 0) {
			factor = 4;
		} else if (config.compareTo(Config.RGB_565) == 0) {
			factor = 2;
		}
		int byteSize = beforeCompress.getHeight() * 
				beforeCompress.getWidth() * factor;
		/*计算quality的值*/
		int quality;
		if (byteSize <= REQUERED_SIZE) {
			quality = 100;
		} else {
			quality = REQUERED_SIZE * 100 / byteSize;
		}
		/*压缩后的图片转化为byte数组*/
		byte[] compressedBmp = null;
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if(beforeCompress.compress(CompressFormat.JPEG, 
            		quality, out)) {
            	compressedBmp = out.toByteArray();
            	out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace(); 
        }
        return compressedBmp;
    }
	
	/**
	 * 从byte得到bitmap的方法
	 * */
	public static Bitmap getPicFromBytes(byte[] bytes, 
			BitmapFactory.Options opts ) {
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 
						0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 
						0, bytes.length);
		return null;
	}
	
	/**
	 * 将输入流转化为byte的方法
	 * */
	public static byte[] readStream(InputStream inStream) 
			throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new 
				ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;
	}
}
