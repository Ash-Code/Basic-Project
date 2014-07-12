package com.badlogic.androidgames;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.FROYO)
public class MediaPlayerTest extends Activity implements OnTouchListener,
		OnPreparedListener {

	TextView textView;

	int explosionID = -1;
	MediaPlayer player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("This is a test for sound Playback!");
		textView.setOnTouchListener(this);
		setContentView(textView);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		player = new MediaPlayer();
		player.setOnPreparedListener(this);
		try {
			AssetManager manager = getAssets();
			AssetFileDescriptor desc = manager.openFd("Frostbite_3.mp3");
			player.setDataSource(desc.getFileDescriptor(),
					desc.getStartOffset(), desc.getLength());
			player.prepare();
			player.setLooping(true);

		} catch (Exception e) {
			textView.setText("Could not load music file ");
			e.printStackTrace();
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (player != null) {
				player.start();
			}
		}

		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (player != null) {
			player.pause();
			if (isFinishing()) {
				player.stop();

				player.release();

			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (player != null) {
			player.start();
		}
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		textView.setText("the file is ready to be played! ");

	}

}
