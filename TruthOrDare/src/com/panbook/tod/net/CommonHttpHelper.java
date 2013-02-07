package com.panbook.tod.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CommonHttpHelper {

	private static final String TAG = "CustomerHttpClient";
	private static final String CHARSET = "gb2312";

	public static String post(String url, NameValuePair... params)
			throws Exception {
		try {
			// 编码参数
			List<NameValuePair> formparams = new ArrayList<NameValuePair>(); // 请求参数
			for (NameValuePair p : params) {
				formparams.add(p);
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					CHARSET);
			// 创建POST请求
			HttpPost request = new HttpPost(url);
			request.setEntity(entity);
			// 发送请求
//			HttpClient client = getHttpClient();
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new RuntimeException("请求失败");
			}
			HttpEntity resEntity = response.getEntity();
			return (resEntity == null) ? null : EntityUtils.toString(resEntity,
					CHARSET);

		} catch (UnsupportedEncodingException e) {
			Log.w(TAG, e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			Log.w(TAG, e.getMessage());
			return null;
		} catch (IOException e) {
			Log.w(TAG, e.getMessage());
			return null;
		} catch (Exception e) {
			throw new Exception("connect error!");
		}

	}

	/** 得到 apache http HttpClient对象 **/
	public static HttpClient getHttpClient() {

		HttpClient httpClient;
		/** 创建 HttpParams 以用来设置 HTTP 参数 **/
		BasicHttpParams httpParams = new BasicHttpParams();

		/** 设置连接超时和 Socket 超时，以及 Socket 缓存大小 **/
		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
		HttpClientParams.setRedirecting(httpParams, true);

		/** 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient **/
		httpClient = new DefaultHttpClient(httpParams);

		return httpClient;

	}
}
