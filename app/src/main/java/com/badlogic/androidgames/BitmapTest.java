package com.badlogic.androidgames;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BitmapTest extends Activity {

	Bitmap cover, cover2;
	
	public class RenderView extends View {

		
		InputStream is;
		BitmapFactory factory;
		AssetManager manager;
		Rect dst = new Rect();

		public RenderView(Context context) {
			super(context);
			manager = getAssets();
			
			try {

				is = manager.open("Cover.jpg");
				cover = BitmapFactory.decodeStream(is);
				Bitmap.Config config = cover.getConfig();
				Log.d("Bitmap test", "Bitmap format is  : " + config);
				System.out.println(config);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				cover2 = BitmapFactory.decodeStream(is, null, options);

			} catch (Exception e) {
				System.out.println("Error in loading file");
				e.printStackTrace();
			}
			finally{
				try{
				is.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		}

		protected void onDraw(Canvas canvas) {
			dst.set(canvas.getWidth() / 2, canvas.getHeight() / 2,
					canvas.getWidth() - 1, canvas.getHeight() - 1);
			
			canvas.drawBitmap(cover, 0, 0, null);
			canvas.drawBitmap(cover2, null, dst, null);
			invalidate();
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));

	}
	
	@Override 
	protected void onPause(){
		super.onPause();
		if(isFinishing()){
			cover.recycle();
		}
	}

}
