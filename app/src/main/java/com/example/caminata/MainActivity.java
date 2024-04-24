package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;


import com.example.base_dato.bd_connection;
import com.example.base_dato.bd_manager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd_connection manager = new bd_connection(MainActivity.this);

        manager.onCreate(manager.getWritableDatabase());
        checkDataBase();

    }
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
         String path = getDatabasePath("sensores_inteligentes.db").toString();
        try {
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            Log.e("Error", "existe la base de datos " );
            checkDB.close();
        } catch (SQLiteException e) {
            Log.e("Error", "No existe la base de datos " + e.getMessage());
        }
        return checkDB != null;
    }
}