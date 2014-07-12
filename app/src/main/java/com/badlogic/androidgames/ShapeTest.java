package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ShapeTest extends Activity {
	
	public class RenderView extends View{
		Paint paint;
		
		
		public RenderView(Context context) {
			super(context);
			paint = new Paint();
		}
		
		@Override 
		protected void onDraw(Canvas canvas){
			paint.setARGB(50, 255, 0, 0);
			canvas.drawLine(0, 0, canvas.getWidth()-1, canvas.getHeight()-1, paint);
			paint.setColor(Color.GRAY);
			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/8, paint);
			invalidate();
			
			
			
			
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new RenderView(this));
		
	}

}
