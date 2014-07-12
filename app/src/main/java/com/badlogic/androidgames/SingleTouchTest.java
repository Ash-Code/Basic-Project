package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener {

	StringBuilder builder = new StringBuilder();
	TextView textview ;
	
	@Override
public void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		textview = new TextView(this);
		textview.setText("Touch and drag");
		textview.setOnTouchListener(this);
		setContentView(textview);
		
	}
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
	
			e1.printStackTrace();
		}
		builder.setLength(0);
		switch (e.getAction()){
		
		case MotionEvent.ACTION_DOWN:
		builder.append("down, ");
		break;
		
		case MotionEvent.ACTION_MOVE:
			builder.append("drag, ");
			break;
			
		case MotionEvent.ACTION_UP:
			builder.append("lifted, ");
			break;
		
		case MotionEvent.ACTION_CANCEL:
			builder.append("Cancel, ");
			break;
		}
		builder.append(e.getX());
		builder.append(" ");
		builder.append(e.getY());
		String text = builder.toString();
		textview.setText(text);
		Log.d("Touch Test", text);
		
		
		
		
		return true;
	}

}

