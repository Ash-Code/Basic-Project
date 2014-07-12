package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {
	StringBuilder builder = new StringBuilder();
	TextView textView;
	float[] x = new float[10];
	float[] y = new float[10];
	boolean[] touched = new boolean[10];
	int[] id = new int[10];
	int[] pi = new int[10];
	int pointerIndex;

	private void updateTextView() {
		builder.setLength(0);
		for (int i = 0; i < 10; i++) {

			builder.append(touched[i]);
			builder.append(", ");
			builder.append(id[i]);
			builder.append(", ");
			builder.append(x[i]);
			builder.append(", ");
			builder.append(y[i]);
			builder.append(", ");
			builder.append(pi[i]);
			builder.append(", ");
			builder.append(pointerIndex);
			builder.append("\n");
		}
		textView.setText(builder.toString());
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Touch and drag (multiple fingers supported)!");
		textView.setOnTouchListener(this);
		setContentView(textView);
		for (int i = 0; i < 10; i++) {
			id[i] = -1;
		}
		updateTextView();
	}

	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getActionMasked();
		pointerIndex = event.findPointerIndex(action);
		int pointerCount = event.getPointerCount();

		for (int i = 0; i < 10; i++) { // iterate through all the pointerIndices
										// coz they take value b/w 0 and
										// pointerCount-1
			if (i >= pointerCount) {
				touched[i] = false;
				id[i] = -1;
				continue;
			}
			if (event.getAction() != MotionEvent.ACTION_MOVE
					&& i != pointerIndex) {

				continue;
			}
			int pointerId = event.getPointerId(i);
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touched[i] = true; // the Values in the arrays Touched, X, Y are
									// stored according to the pointerIndex
				pi[i] = i;
				id[i] = pointerId;
				x[i] = (int) event.getX(i);
				y[i] = (int) event.getY(i);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_OUTSIDE:
			case MotionEvent.ACTION_CANCEL:
				touched[i] = false;
				id[i] = -1;
				x[i] = (int) event.getX(i);
				y[i] = (int) event.getY(i);
				break;
			case MotionEvent.ACTION_MOVE:
				touched[i] = true;
				id[i] = pointerId;
				x[i] = (int) event.getX(i);
				y[i] = (int) event.getY(i);
				break;
			}
		}
		updateTextView();
		return true;
	}
}