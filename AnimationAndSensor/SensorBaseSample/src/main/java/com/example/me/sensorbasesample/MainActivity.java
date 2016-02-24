package com.example.me.sensorbasesample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    private Sensor ligthSensor;
    private StringBuffer sb;
    private TextView tvValue;
    private MySensorListener mySensorListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = new StringBuffer();
        tvValue = (TextView) findViewById(R.id.tv_value);

        //获取SensorManager对象
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取Sensor对象
        ligthSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        mySensorListener=new MySensorListener();

    }

    @Override
    protected void onResume() {
        sm.registerListener(mySensorListener, ligthSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(mySensorListener);
        super.onPause();
    }

    public class MySensorListener implements SensorEventListener {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent event) {
            //获取精度
            float acc = event.accuracy;
            //获取光线强度
            float lux = event.values[0];

            sb.setLength(0);
            sb.append("精度 ----> " + acc);
            sb.append("\n");
            sb.append("光线强度 ----> " + lux);
            sb.append("\n");

            tvValue.setText(sb.toString());
        }

    }
}
