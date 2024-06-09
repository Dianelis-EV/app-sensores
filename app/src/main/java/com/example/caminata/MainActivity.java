package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import com.example.base_dato.bd_connection;
import com.example.base_dato.bd_manager;
import com.example.caminata.service.Encriptacion;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //prueba
      //  manager.person_deleteAll();
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Aquí puedes iniciar una nueva actividad o realizar otra acción
                // Por ejemplo, iniciar una nueva actividad llamada 'NuevaActividad'
                String user = LoginActivity.leerValor(MainActivity.this, "user");
                String pass = LoginActivity.leerValor(MainActivity.this, "password");

                if(!user.equals("") && !pass.equals("")){
                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza la MainActivity actual
                }

            }
        }, 5000);


    }

}