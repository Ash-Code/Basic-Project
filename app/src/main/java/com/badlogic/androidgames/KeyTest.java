package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class KeyTest extends Activity implements OnKeyListener {

	StringBuilder builder;
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		builder = new StringBuilder();
		textView = new TextView(this);
		textView.setText("Press Keys!");
		textView.setOnKeyListener(this);
		setContentView(textView);
		textView.setFocusableInTouchMode(true);
		textView.requestFocus();

	}

	@Override
	public boolean onKey(View view, int keycode, KeyEvent event) {

		switch (event.getAction()) {

		case KeyEvent.ACTION_DOWN:
			builder.append("down ");
			break;
		case KeyEvent.ACTION_UP:
			builder.append("up ");
			break;
		}

		builder.append(keycode);
		builder.append(", ");
		builder.append(event.getKeyCode());
		builder.append(", ");
		builder.append((char) event.getUnicodeChar());
		String text = builder.toString();
		textView.setText(text);

		return event.getKeyCode() != KeyEvent.KEYCODE_BACK;

	}

}
