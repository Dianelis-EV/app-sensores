package com.example.getsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class sensorMagnetometro implements SensorEventListener , sensorValues {

    private float[] magnetometroValues;

    public sensorMagnetometro() {
        this.magnetometroValues = new float[3];
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometroValues = sensorEvent.values;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public float[] getValues() {
        return magnetometroValues;
    }
}