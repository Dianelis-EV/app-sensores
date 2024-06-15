package com.example.caminata;

import static com.example.caminata.R.id.crearCuenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.base_dato.bd_manager;
import com.example.basedatos.SesionAbierta.GetUser;
import com.example.basedatos.bd_manager;
//import com.example.base_dato.bd_manager;
import com.example.caminata.service.Encriptacion;
import com.example.interfazprincipal.ParticipanteActivity;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Mi codigo
        new_acount();
        login();
    }

    protected void new_acount(){
        TextView newAcount = (TextView) findViewById(R.id.crearCuenta);
        newAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, createAcount.class);
                startActivity(intent);
            }
        });
    }

   protected void login(){
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
       EditText newName = (EditText) findViewById(R.id.accountUser);
       EditText newpass = (EditText) findViewById(R.id.accountpassword);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newName.getText().toString().equals("") && !newpass.getText().toString().equals("")) {
                    String name = (String) newName.getText().toString();
                    String password = (String) newpass.getText().toString();
                    bd_manager manager = new bd_manager(LoginActivity.this);
                    try{
                        if(manager.getpassword(name).equals(Encriptacion.getencriptacion(password))){
                            GetUser.guardarValor(LoginActivity.this, "user", name);
                            GetUser.guardarValor(LoginActivity.this, "password", Encriptacion.getencriptacion(password));
                            Intent intent = new Intent(LoginActivity.this, ParticipanteActivity .class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this,"Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }catch (Exception e){
                        Toast.makeText(LoginActivity.this,"Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG)
                                .show();
                    }


                }else{
                    Toast.makeText(LoginActivity.this, "Campos obligatorios vacios", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }




}