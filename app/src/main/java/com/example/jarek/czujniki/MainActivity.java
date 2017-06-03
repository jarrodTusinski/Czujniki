package com.example.jarek.czujniki;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sm;
    private TextView azimuth;
    private TextView pitch;
    private TextView roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Shows list of all sensors in the device.
         */
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        TextView tv = (TextView) findViewById(R.id.czujniki);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor: sensors)
            tv.append(sensor.getName() + " "
                    + sensor.getVendor() + '\n');
        sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        azimuth = (TextView) findViewById(R.id.azimuth);
        pitch = (TextView) findViewById(R.id.pitch);
        roll = (TextView) findViewById(R.id.roll);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        azimuth.setText("y = " + Float.toString(event.values[0]));
        pitch.setText("x = " + Float.toString(event.values[1]));
        roll.setText("z = " + Float.toString(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}