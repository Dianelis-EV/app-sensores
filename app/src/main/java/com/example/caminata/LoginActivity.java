package com.example.caminata;

import static com.example.caminata.R.id.crearCuenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.base_dato.bd_manager;

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
                if(newName.getText().toString().equals("") && newpass.getText().toString().equals("")) {
                    String name = (String) newName.getText().toString();
                    String password = (String) newpass.getText().toString();
                }

                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
}