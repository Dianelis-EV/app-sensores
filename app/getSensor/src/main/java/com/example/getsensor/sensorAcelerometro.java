package com.example.getsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.ArrayList;

public class sensorAcelerometro implements SensorEventListener, sensorValues {
   float[] acelerometroValues;

    public sensorAcelerometro() {
        this.acelerometroValues = new float[3];
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            acelerometroValues = sensorEvent.values;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    @Override
    public float[] getValues() {
        return acelerometroValues;
    }
}
