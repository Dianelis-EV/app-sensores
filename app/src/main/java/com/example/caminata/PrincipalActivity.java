package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basedatos.SesionAbierta.GetUser;
import com.example.basedatos.bd_manager;
import com.example.interfazprincipal.ParticipanteActivity;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        agregar_participante();
        Mensaje();
        visibles();
    }

    public void agregar_participante(){
        LinearLayout linearLayoutParticipante = (LinearLayout) findViewById(R.id.AddParticipante);

        linearLayoutParticipante.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, ParticipanteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Mensaje(){
        TextView mensaje = (TextView) findViewById(R.id.textViewPrincipalUsuario);
        CharSequence frase = mensaje.getText().toString() +" "+GetUser.leerValor(this,"user");
        mensaje.setText(frase);
    }

    public void visibles(){
        LinearLayout linearLayoutparticipante1 = (LinearLayout) findViewById(R.id.ParticipanteName2);
        TextView textViewname1 = (TextView) findViewById(R.id.textViewParticipanteName2);
        LinearLayout linearLayoutparticipante2 = (LinearLayout) findViewById(R.id.ParticipanteName);
        TextView textViewname2 = (TextView) findViewById(R.id.textViewParticipanteName);
        TextView textViewAll = (TextView) findViewById(R.id.textViewAll);
        bd_manager manager = new bd_manager(this);
        List<String> nameList= manager.person_namelist();
        int cant = manager.person_count();
        if(cant != -1){
            if(!nameList.isEmpty()){
                if(nameList.size()==1){
                    CharSequence name = nameList.get(0);
                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    textViewname1.setText(name);
                }else if(nameList.size()==2){
                    CharSequence name = nameList.get(0);
                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    textViewname1.setText(name);
                    CharSequence name2 = nameList.get(1);
                    linearLayoutparticipante2.setVisibility(View.VISIBLE);
                    textViewname2.setText(name2);
                }else {
                    CharSequence name = nameList.get(0);
                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    textViewname1.setText(name);
                    CharSequence name2 = nameList.get(1);
                    linearLayoutparticipante2.setVisibility(View.VISIBLE);
                    textViewAll.setVisibility(View.VISIBLE);
                    textViewname2.setText(name2);
                }
            }
        }
    }

}