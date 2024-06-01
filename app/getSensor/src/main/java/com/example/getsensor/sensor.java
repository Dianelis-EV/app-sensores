package com.example.getsensor;

import android.util.Log;

public class sensor {

    sensorValues values;

    public sensor(sensorValues values) {
        this.values = values;

    }

    public void datos(){
        Log.d("Mensaje", "Los valores son:"+ values.getValues());
    }
}
