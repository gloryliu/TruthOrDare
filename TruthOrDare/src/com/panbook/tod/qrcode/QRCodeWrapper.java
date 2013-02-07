/***************************************************************************
 *
 * Copyright (c) 2012 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/

/**   
* @Title: QRCodeWrapper.java 
* @Package com.panbook.tod.qrcode 
* @Description: TODO 
* @author shupan@baidu.com
* @date 2012-12-6 下午03:09:06
* @version V1.0   
*/ 


package com.panbook.tod.qrcode;

import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.util.Log;

import com.panbook.tod.net.CommonHttpHelper;
import com.panbook.tod.utils.ImageTool;

public class QRCodeWrapper {

	
	public static final String GOOGLE_QRCODE_API = "http://chart.apis.google.com/chart?chs=500x500&cht=qr";
	
	public static void requestQRCode(Map<String, String> uriParams, Map<String, String> posgParams) {
		
	}
	
	public static Bitmap getQRCode(String data) {

		NameValuePair postParam1 = new BasicNameValuePair("key", "value");
		NameValuePair postParam2 = new BasicNameValuePair("chl", data);
	
		String response = null;
		try {
//			 response = CommonHttpHelper.post(GOOGLE_QRCODE_API, postParam1, postParam2);
//			 response = CommonHttpHelper.post("http://baike.baidu.com/view/1790469.htm");
			 response = CommonHttpHelper.post("http://appbuilder.duapp.com/");
		} catch (Exception e) {
			// TODO: handle exception
			Log.w("panbook", e.getMessage());
		}
		
		return ImageTool.bytesToBimap(response.getBytes());
	}
}



