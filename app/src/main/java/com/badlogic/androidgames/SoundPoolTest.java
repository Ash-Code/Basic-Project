package com.badlogic.androidgames;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.FROYO)
public class SoundPoolTest extends Activity implements OnTouchListener, OnLoadCompleteListener {

	TextView textView;

	int explosionID = -1;
	SoundPool soundpool;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("This is a test for sound Playback! Please wait till BF3 Sound is loaded...");
		textView.setOnTouchListener(this);
		setContentView(textView);
		
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundpool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		soundpool.setOnLoadCompleteListener(this);
		try {
			AssetManager manager = getAssets();
			AssetFileDescriptor desc = manager.openFd("explosion.ogg");
			explosionID = soundpool.load(desc, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (explosionID != -1) {
				
				soundpool.play(explosionID, 1.0f, 1.0f, 0, 0, 1);
			}
		}

		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		soundpool.autoPause();
if(isFinishing()){
	
	soundpool.unload(explosionID);
	soundpool.release();
			
		}

	}
	@Override 
	protected void onResume() {
		super.onResume();
		
		soundpool.autoResume();
	}

	@Override
	public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
		textView.setText("Sound File loaded successfully. Touch to play XD ");
		
	}

}
