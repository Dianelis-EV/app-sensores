package com.example.interfazprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.basedatos.bd_manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParticipanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante);

        //RadioButton
       selecionados();


        //Button Guardar
        Button boton = (Button) findViewById(R.id.buttonGuardar);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               try{
                   Guardar_participante();
                   Toast.makeText(ParticipanteActivity.this, "El id es " + id_baseDatos(), Toast.LENGTH_SHORT).show();
               }catch (Exception e){
                   Toast.makeText(ParticipanteActivity.this, "Ocurrio un error al insertar los datos", Toast.LENGTH_SHORT).show();
               }
            }
        } );
    }

    public void Guardar_participante(){
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        //Guardar en la base de datos
        if(!nombre.getText().toString().equals("") && ci_iscorrect(ci.getText().toString())){
            if(id_baseDatos()!=-1){
                if(telefono_iscorrect(telefono.getText().toString())){
                    if(femenino.isChecked()){
                        bd_manager manager = new bd_manager(ParticipanteActivity.this);
                        manager.person_insert(nombre.getText().toString(),ci.getText().toString(),
                                femenino.getText().toString(), telefono.getText().toString());
                    }else if(masculino.isChecked()){
                        bd_manager manager = new bd_manager(ParticipanteActivity.this);
                        manager.person_insert(nombre.getText().toString(),ci.getText().toString(),
                                femenino.getText().toString(), telefono.getText().toString());
                    }else{
                        Toast.makeText(this, "Por favor seleccione un sexo", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "El número de telèfono es incorrecto", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "El participante ya ha sido registrado antes", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Nombre o carnet incorrecto", Toast.LENGTH_LONG).show();
        }
    }

    public int id_baseDatos(){
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        bd_manager manager = new bd_manager(ParticipanteActivity.this);
        int id = manager.person_getID(ci.getText().toString());
        return id;
    }

    public void Guardar_datos_participante(){

    }

    public void selecionados(){
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        femenino.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    masculino.setChecked(false);
                }
            }
        });
        masculino.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    femenino.setChecked(false);
                }
            }
        });
    }

    public boolean ci_iscorrect(String carnet){
        boolean iscorrect = true;
        // Extraer la fecha de nacimiento y el siglo
        String fechaNacimiento = carnet.substring(0, 6);
        char siglo = carnet.charAt(6);

            // Verificar la longitud del carnet
            if (carnet == null || carnet.length() != 11) {
                iscorrect = false;
            }else{

                // Verificar la fecha de nacimiento
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
                sdf.setLenient(false);
                try {
                    sdf.parse(fechaNacimiento);
                } catch (ParseException e) {
                    iscorrect = false;
                }

                // Verificar el siglo
                if (siglo < '0' || siglo > '8') {
                    iscorrect = false;
                }

                // Verificar el sexo
                int sexo = Character.getNumericValue(carnet.charAt(9));
                if (sexo < 0 || sexo > 9 || (sexo % 2 == 0 && sexo != 0)) {
                    iscorrect = false;
                }

            }

            // Si todas las verificaciones son correctas
            return iscorrect;
    }

    public boolean telefono_iscorrect(String telefono){
        boolean iscorrect = false;
        if(telefono !=null && telefono.length() == 8){
            iscorrect = true;
        }
        return iscorrect;
    }
}