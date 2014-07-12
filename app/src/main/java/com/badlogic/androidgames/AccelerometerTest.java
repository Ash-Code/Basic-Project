package com.badlogic.androidgames;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener {
	TextView textView;
	StringBuilder builder = new StringBuilder();
	SensorManager manager;
	Sensor sensor;
	Boolean hasAccel, success;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Accelerometer Test!");
		setContentView(textView);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		hasAccel = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() > 0;
		
		if (hasAccel) {
			sensor = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			success = manager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
			if (!success) {
				textView.setText("Couldn't register Sensor. ");
			} else {
				textView.setText("No Accelerometer found ");
			}

		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x,y,z;
		x=event.values[0];
		y=event.values[1];
		z=event.values[2];
		
		builder.setLength(0);
		builder.append(" X : " + x);
		builder.append(", ");
		builder.append(" Y : " + y);
		builder.append(", ");
		builder.append(" Z : " + z);
		
		textView.setText(builder.toString());
		

	}

}
