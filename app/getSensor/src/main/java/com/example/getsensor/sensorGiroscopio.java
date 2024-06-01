package com.example.getsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class sensorGiroscopio implements SensorEventListener , sensorValues {

    private float[] giroscopioValues;


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            giroscopioValues = sensorEvent.values;
            float a = sensorEvent.values[0];

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    @Override
    public float[] getValues() {

        return giroscopioValues;
    }

    public sensorGiroscopio() {
        this.giroscopioValues = new float[3];
    }
}
