/***************************************************************************
 *
 * Copyright (c) 2012 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/

/**   
* @Title: SelectPortrait.java 
* @Package com.panbook.tod 
* @Description: TODO 
* @author shupan@baidu.com
* @date 2012-12-7 下午04:37:52
* @version V1.0   
*/ 


package com.panbook.tod;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SelectPortraitActivity extends Activity {
	
	private Button startBtn;
	private Button stopBtn;
	private ImageView portraitImage;
	private AnimationDrawable animDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_portrait);
		
		startBtn = (Button)findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animDrawable.start();
			}
		});
		
		stopBtn = (Button)findViewById(R.id.stopBtn);
		stopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animDrawable.stop();
			}
		});
		
		portraitImage = (ImageView)findViewById(R.id.imageSelectPortrait);
	
		animDrawable = new AnimationDrawable();		
		for(int i = 1; i < 20; i++) {
			int id = getResources().getIdentifier("p" + i, "drawable", getPackageName());
			animDrawable.addFrame(getResources().getDrawable(id), 100);
		}
	
		animDrawable.setOneShot(false);
		
		portraitImage.setImageDrawable(animDrawable);
	}
}



