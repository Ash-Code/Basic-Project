package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class FullScreenTest extends Activity {
	TextView textView;
	PowerManager manager;
	WakeLock lock;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		textView = new TextView(this);
		textView.setText("This app must go fullscreen and stay awake");
		setContentView(textView);
		manager = (PowerManager)getSystemService(POWER_SERVICE);
		lock = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "lock");

	}

	@Override
	protected void onStart() {
		super.onStart();
		lock.acquire();
	}

	@Override
	protected void onResume() {
		super.onResume();
		lock.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();
		lock.release();
	}

}
