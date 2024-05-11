package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.base_dato.bd_manager;
import com.example.caminata.model.user;

public class createAcount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);

        createnewacount();
    }

    protected void createnewacount(){
        Button sigin = (Button) findViewById(R.id.buttonsigin);
        EditText newName = (EditText) findViewById(R.id.newaccountUser);
        EditText newpass = (EditText) findViewById(R.id.newaccountpassword);
        EditText repitnewpass = (EditText) findViewById(R.id.repetircontrasenna);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd_manager manager = new bd_manager(createAcount.this);
                if(newName.getText().toString().equals("") && newpass.getText().toString().equals("") &&repitnewpass.getText().toString().equals("")){
                    String name = (String) newName.getText().toString();
                    String password = (String) newpass.getText().toString();
                    String pass = (String) repitnewpass.getText().toString();
                    manager.user_insert(name , password , pass);
                    Intent intent = new Intent(createAcount.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}