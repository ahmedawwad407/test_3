package com.example.m_lightsensore;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    ConstraintLayout constraintLayout;
    TextView textView;

// bransh master tset1
// bransh Ahmed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.valueS);
       constraintLayout = findViewById(R.id.mainLayout);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            Toast.makeText(this, " sensor is not found", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {

            int val = (int) sensorEvent.values[0];
            textView.setText("val: " + val + "\nMax Val is: " + sensorEvent.sensor.getMaximumRange());

            if (val < 50) {
                constraintLayout.setBackgroundResource(R.drawable.night);
            } else {

                constraintLayout.setBackgroundResource(R.drawable.morning);
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}