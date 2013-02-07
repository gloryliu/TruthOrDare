package com.panbook.tod;

import java.io.File;
import java.net.URLEncoder;

import com.panbook.tod.net.HttpDownloader;
import com.panbook.tod.qrcode.QRCodeWrapper;
import com.panbook.tod.utils.FileUtils;
import com.panbook.tod.utils.ImageTool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class TruthOrDareActivity extends Activity {
	
	private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/_test_tod/";    

	private Bitmap bitmapPortrait;
	private Bitmap bitmapGrayPortrait;
	private Bitmap bitmapQRCode;
	
	private ImageView imagePortrait;
	private ImageView imageGrayPortrait;
	private ImageView imageQRCode;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        imagePortrait = (ImageView) findViewById(R.id.imagePortrait);
        imageGrayPortrait = (ImageView) findViewById(R.id.imageGrayPortrait);
        imageQRCode = (ImageView) findViewById(R.id.imageQRCode);
        
        getQRCodetest();
        
    }
    
    
    
    public void getQRCodetest() {
        String picPath = "ginko.png";
//      bitmap = BitmapFactory.decodeFile(picPath);
        bitmapPortrait = FileUtils.getBitmapFromAssets(this, picPath);
        byte[] oldBitmapBytes = ImageTool.bitmapToBytes(bitmapPortrait);
        Log.i("panbook", new String(oldBitmapBytes));
        System.out.println("bitmap length:" + oldBitmapBytes.length);
        
        // 灰度变换
        bitmapGrayPortrait = ImageTool.toGrayscale(bitmapPortrait);
        byte[] grayBitmapBytes = ImageTool.bitmapToBytes(bitmapGrayPortrait);
        System.out.println("gray bitmap length:" + grayBitmapBytes.length);
        
        // 获取二维码
//      bitmapQRCode = QRCodeWrapper.getQRCode(new String(grayBitmapBytes));
        String imageUrl = "http://chart.apis.google.com/chart?chs=500x600&cht=qr&chld=L|1";
//      chl = new String(grayBitmapBytes);
        String grayBitmapStr = new String(grayBitmapBytes);
        String params = "chl=" + URLEncoder.encode(grayBitmapStr);
        System.out.println(params.length());
        bitmapQRCode = HttpDownloader.downloadImagePost(imageUrl, params);
        File dirFile = new File(ALBUM_PATH);    
        if(!dirFile.exists()){    
            dirFile.mkdir();    
        }
        String fullPath = ALBUM_PATH + "11.jpg";
        ImageTool.saveJPGE_After(bitmapQRCode, fullPath);
        displayImage();
    }
    
    /**
     * 展示图片
     */
    public void displayImage() {
    	
    	imagePortrait.setImageBitmap(bitmapPortrait);
    	imageGrayPortrait.setImageBitmap(bitmapGrayPortrait);
    	imageQRCode.setImageBitmap(bitmapQRCode);
    }
}