package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;


import com.example.base_dato.bd_connection;
import com.example.base_dato.bd_manager;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       bd_manager manager = new bd_manager(MainActivity.this);
       //prueba
        //manager.person_deleteAll();
        manager.data_deleteAll();
        //manager.person_insert("Dianelis", "01090168017","Femenino", "72802153");
        manager.data_insert("01090168017",22,0,36.5,54.03,45.32,5.25,"Problemas en la rodilla");
        manager.data_update("01090168017",manager.getDate(),23,0,36.5,54.03,45.32,5.25,"Problemas en la rodilla");
        ArrayList<String> list = manager.data_list();
        manager.data_delete("01090168017", list.get(1));

    }

}