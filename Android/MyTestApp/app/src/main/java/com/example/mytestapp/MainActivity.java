package com.example.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener,View.OnClickListener {

    private SensorManager mSensorMgr;
    private TextView tvx;
    private TextView tvy;
    private TextView tvz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.bt_dsp);
        bt.setOnClickListener(this);

        Button bt_stop = findViewById(R.id.bt_stop);
        bt_stop.setOnClickListener(this);

        tvx = findViewById(R.id.tvx);
        tvy = findViewById(R.id.tvy);
        tvz = findViewById(R.id.tvz);
        //
        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    protected void onPause() {
        super.onPause();
        mSensorMgr.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
        mSensorMgr.unregisterListener(this);

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;

            tvx.setText("ACC_X: " + Float.toString(values[0]));
            tvy.setText("ACC_Y: " + Float.toString(values[1]));
            tvz.setText("ACC_Z: " + Float.toString(values[2]));

        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {//不用处理，空着就行
        return;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.bt_dsp) {
            mSensorMgr.unregisterListener(this, mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            mSensorMgr.registerListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);
            return;
        }
        if (v.getId() == R.id.bt_stop) {
            mSensorMgr.unregisterListener(this);
            return;
        }
    }
}