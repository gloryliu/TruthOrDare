package com.panbook.tod.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpDownloader {

	/**
	 * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容
	 * 1.创建一个URL对象
	 * 2.通过URL对象，创建一个HttpURLConnection对象
	 * 3.得到InputStram
	 * 4.从InputStream当中读取数据
	 * @param urlStr
	 * @return
	 */
	public static String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			// 创建一个URL对象
			URL url = new URL(urlStr);
			// 创建一个Http连接
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			// 使用IO流读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	

	/**
	 * 通过post方式download
	 * @param urlStr
	 * @param chl
	 * @return
	 */
	public static String downloadPost(String urlStr, Map<String, String> postParams) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			// 创建一个URL对象
			URL url = new URL(urlStr);
			// 创建一个Http连接
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setRequestMethod("POST");
			
			Collection<String> c = postParams.values();
			Iterator<String> it = c.iterator();
			String k, v;
			while(it.hasNext()) {
				k = it.next();
				urlConn.setRequestProperty(k, postParams.get(k));
			}
			
//			urlConn.setRequestProperty("chl", chl);
			// 使用IO流读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static Bitmap downloadImage(String imageUrl) {

		Bitmap bitmap = null;
		try {
			URL myurl = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bitmap;
	}
	
	
	/**
	 * 下载二维码
	 * @param imageUrl
	 * @param chl 
	 * @return
	 */
	public static Bitmap downloadImagePost(String imageUrl, String params) {

		Bitmap bitmap = null;
		try {
			URL myurl = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.getOutputStream().write(params.getBytes());
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bitmap;
	}
}
