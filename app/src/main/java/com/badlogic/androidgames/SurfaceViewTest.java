package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {

	FastRenderView renderView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		renderView = new FastRenderView(this);
		setContentView(renderView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		renderView.Pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		renderView.Resume();
	}

	public class FastRenderView extends SurfaceView implements Runnable {
		SurfaceHolder holder;
		Thread renderThread = null;
		volatile Boolean running = false;

		public FastRenderView(Context context) {
			super(context);
			holder = getHolder();
		}

		@Override
		public void run() {

			while (running) {
				if (!holder.getSurface().isValid()) {
					continue;
				}
				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.BLUE);
				holder.unlockCanvasAndPost(canvas);
			}

		}

		public void Pause() {
			running = false;
			while (true) {
				try {
					renderThread.join();
					return;

				} catch (Exception e) {

				}
			}

		}

		public void Resume() {
			running = true;
			renderThread = new Thread(this);
			renderThread.start();

		}

	}

}
