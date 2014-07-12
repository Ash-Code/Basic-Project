package com.badlogic.androidgames;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	TextView view =new TextView(this);
	view.setText("This app was developed by Ashwin Kumar with help from Beginning android 4 games 2nd Edition. Thank you for viewing!");
	setContentView(view);
}
	
}
