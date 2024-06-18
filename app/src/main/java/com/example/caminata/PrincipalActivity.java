package com.example.caminata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basedatos.SesionAbierta.GetUser;
import com.example.basedatos.bd_manager;
import com.example.basedatos.model.Person;
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
        LinearLayout linearLayoutparticipante1 = (LinearLayout) findViewById(R.id.ParticipanteName);
        TextView textViewParticipanteName = (TextView) findViewById(R.id.textViewParticipanteName);
        TextView textViewCarnetPrincipal = (TextView) findViewById(R.id.textViewCarnetPrincipal);
        TextView textViewTelefonoPrincipal = (TextView) findViewById(R.id.textViewTelefonoPrincipal);

        LinearLayout linearLayoutparticipante2 = (LinearLayout) findViewById(R.id.ParticipanteName2);
        TextView textViewname2 = (TextView) findViewById(R.id.textViewParticipanteName2);
        TextView textViewCarnetPrincipal2= (TextView) findViewById(R.id.textViewCarnetPrincipal2);
        TextView textViewTelefonoPrincipal2 = (TextView) findViewById(R.id.textViewTelefonoPrincipal2);
        TextView textViewAll = (TextView) findViewById(R.id.textViewAll);
        TextView textViewparticipante = (TextView) findViewById(R.id.textView9);

        bd_manager manager = new bd_manager(this);
        ArrayList<Person> nameList= manager.person_list();
        int cant = manager.person_count();
        if(cant != -1){
            if(!nameList.isEmpty()){
                if(nameList.size()==1){
                    textViewparticipante.setVisibility(View.VISIBLE);
                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    CharSequence name = nameList.get(0).getName();
                    CharSequence ci = nameList.get(0).getCi();
                    CharSequence telef = nameList.get(0).getTelefono();
                    textViewParticipanteName.setText(name);
                    textViewCarnetPrincipal.setText(ci);
                    textViewTelefonoPrincipal.setText(telef);
                }else if(nameList.size()==2){
                    textViewparticipante.setVisibility(View.VISIBLE);

                    CharSequence name = nameList.get(0).getName();
                    CharSequence ci = nameList.get(0).getCi();
                    CharSequence telef = nameList.get(0).getTelefono();
                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    textViewParticipanteName.setText(name);
                    textViewCarnetPrincipal.setText(ci);
                    textViewTelefonoPrincipal.setText(telef);

                    CharSequence name2 = nameList.get(1).getName();
                    CharSequence ci2 = nameList.get(2).getCi();
                    CharSequence telef2 = nameList.get(2).getTelefono();
                    linearLayoutparticipante2.setVisibility(View.VISIBLE);
                    textViewname2.setText(name2);
                    textViewCarnetPrincipal2.setText(ci2);
                    textViewTelefonoPrincipal2.setText(telef2);
                }else {
                    textViewAll.setVisibility(View.VISIBLE);
                    textViewparticipante.setVisibility(View.VISIBLE);

                    linearLayoutparticipante1.setVisibility(View.VISIBLE);
                    CharSequence name = nameList.get(0).getName();
                    CharSequence ci = nameList.get(0).getCi();
                    CharSequence telef = nameList.get(0).getTelefono();
                    textViewParticipanteName.setText(name);
                    textViewCarnetPrincipal.setText(ci);
                    textViewTelefonoPrincipal.setText(telef);

                    linearLayoutparticipante2.setVisibility(View.VISIBLE);
                    CharSequence name2 = nameList.get(1).getName();
                    CharSequence ci2 = nameList.get(1).getCi();
                    CharSequence telef2 = nameList.get(1).getTelefono();
                    textViewname2.setText(name2);
                    textViewCarnetPrincipal2.setText(ci2);
                    textViewTelefonoPrincipal2.setText(telef2);

                }
            }
        }
    }

}