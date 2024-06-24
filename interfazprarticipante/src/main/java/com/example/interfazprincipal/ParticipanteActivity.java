package com.example.interfazprincipal;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basedatos.SesionAbierta.GetUser;
import com.example.basedatos.bd_manager;
import com.example.basedatos.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParticipanteActivity extends AppCompatActivity {

    private String mensaje = "insertar";
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante);

        Bundle recibirdatos = getIntent().getExtras();
        if(recibirdatos != null){
            person =(Person) recibirdatos.get("persona");
            llenar_datos();
            isnotEnable();
        }

            //RadioButton
            selecionados();
            TodosLosParticipantesActivity todos = new TodosLosParticipantesActivity();

            //Button Guardar
            Button boton = (Button) findViewById(R.id.buttonGuardar);
            boton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    try{
                            if(person == null){
                                if(datosCorrectos()){
                                    Guardar_participante();
                                    Guardar_datos_participante();
                                    Toast.makeText(ParticipanteActivity.this, "Datos Insertados", Toast.LENGTH_SHORT).show();
                                }
                            } else{

                                if(datos_modificados_correctos()){
                                    modificar();
                                    Toast.makeText(ParticipanteActivity.this, "Datos nodificados", Toast.LENGTH_SHORT).show();
                                }
                            }
                    }catch (Exception e){
                        Toast.makeText(ParticipanteActivity.this, "Ocurrio un error al insertar los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            } );

            ImageButton atras = (ImageButton) findViewById(R.id.atrasLista) ;
            atras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ParticipanteActivity.this,TodosLosParticipantesActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    public void Guardar_participante(){
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        String user = GetUser.leerValor(this, "user");
        //Guardar en la base de datos

                    if(femenino.isChecked()){
                        bd_manager manager = new bd_manager(ParticipanteActivity.this);
                        manager.person_insert(user,nombre.getText().toString(),ci.getText().toString(),
                                femenino.getText().toString(), telefono.getText().toString());
                    }else{
                        bd_manager manager = new bd_manager(ParticipanteActivity.this);
                        manager.person_insert(user,nombre.getText().toString(),ci.getText().toString(),
                                masculino.getText().toString(), telefono.getText().toString());
                    }

    }

    public int id_baseDatos(){
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        int id=0;
        if(person == null){
            bd_manager manager = new bd_manager(ParticipanteActivity.this);
            id = manager.person_getID(ci.getText().toString());
        }
        return id;
    }

    public void Guardar_datos_participante(){
        EditText editTextEdad = (EditText) findViewById(R.id.editTextNumberEdad);
        EditText editTextCalzado = (EditText) findViewById(R.id.editTextNumberCalzado);
        EditText editTextMedicion = (EditText) findViewById(R.id.editTextNumberMedicion);
        EditText editTextPierna = (EditText) findViewById(R.id.editTextNumberPierna);
        EditText editTextAltura = (EditText) findViewById(R.id.editTextNumberAltura);
        EditText editTextObservacion = (EditText) findViewById(R.id.editObservacion);
        RadioButton radioButtonsi = (RadioButton) findViewById(R.id.radioButtonSi);
        RadioButton radioButtonno = (RadioButton) findViewById(R.id.radioButtonNo);
        EditText ci = (EditText) findViewById(R.id.editTextCI);


            if(radioButtonsi.isChecked()){
                    bd_manager manager = new bd_manager(ParticipanteActivity.this);
                    manager.data_insert(ci.getText().toString(),Integer.valueOf(editTextEdad.getText().toString()),
                           1,Double.valueOf(editTextCalzado.getText().toString()),
                            Double.valueOf(editTextMedicion.getText().toString()),Double.valueOf(editTextPierna.getText().toString()),
                            Double.valueOf(editTextAltura.getText().toString()),editTextObservacion.getText().toString());
            }else{
                bd_manager manager = new bd_manager(ParticipanteActivity.this);
                manager.data_insert(ci.getText().toString(),Integer.valueOf(editTextEdad.getText().toString()),
                        0,Double.valueOf(editTextCalzado.getText().toString()),
                        Double.valueOf(editTextMedicion.getText().toString()),Double.valueOf(editTextPierna.getText().toString()),
                        Double.valueOf(editTextAltura.getText().toString()),null);
            }
    }

    private boolean datosCorrectos() {
        boolean datosCorrectos = false;
        //-------------------------------------------------------------------
        EditText editTextEdad = (EditText) findViewById(R.id.editTextNumberEdad);
        EditText editTextCalzado = (EditText) findViewById(R.id.editTextNumberCalzado);
        EditText editTextMedicion = (EditText) findViewById(R.id.editTextNumberMedicion);
        EditText editTextPierna = (EditText) findViewById(R.id.editTextNumberPierna);
        EditText editTextAltura = (EditText) findViewById(R.id.editTextNumberAltura);
        EditText editTextObservacion = (EditText) findViewById(R.id.editObservacion);
        RadioButton radioButtonsi = (RadioButton) findViewById(R.id.radioButtonSi);
        RadioButton radioButtonno = (RadioButton) findViewById(R.id.radioButtonNo);
        //------------------------------------------------------------------------
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);


        if(!nombre.getText().toString().equals("") ){
            if(ci_iscorrect(ci.getText().toString())){
                if(id_baseDatos()==-1){
                    if(telefono_iscorrect(telefono.getText().toString())){
                        if(femenino.isChecked()|| masculino.isChecked()){
                            if(!editTextEdad.getText().toString().equals("")){
                                if(Integer.valueOf(editTextEdad.getText().toString())<115){
                                    if(!editTextCalzado.getText().toString().equals("")){
                                        if(!editTextMedicion.getText().toString().equals("")){
                                            if(!editTextPierna.getText().toString().equals("")){
                                                if(!editTextAltura.getText().toString().equals("")){
                                                    if(radioButtonsi.isChecked()){
                                                        if(!editTextObservacion.getText().toString().equals("")){
                                                            datosCorrectos = true;
                                                        }else{
                                                               Toast.makeText(this, "Por favor ingrese la patologia " +
                                                                               "que presenta",
                                                                       Toast.LENGTH_LONG).show();
                                                           }
                                                    }else if (radioButtonno.isChecked()){
                                                           datosCorrectos = true;
                                                    }else{
                                                        Toast.makeText(this, "Por favor seleccione si presenta una patologia ",
                                                                Toast.LENGTH_LONG).show();
                                                    }

                                                }else{
                                                    Toast.makeText(this, "Por favor ingrese la altura " +
                                                                    "a la que se encuentra el sensor",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }else{
                                                Toast.makeText(this, "Por favor ingrese " +
                                                        "el largo de la pierna", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                               Toast.makeText(this, "Por favor ingrese la medición" +
                                                       " de la cintura al tobillo", Toast.LENGTH_LONG).show();
                                           }
                                    }else{
                                         Toast.makeText(this, "Por favor ingrese el " +
                                                   "número de calzado", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                       Toast.makeText(this, "Por favor ingrese " +
                                               "una edad correcta", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                   Toast.makeText(this, "Por favor ingrese " +
                                           "una edad ", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(this, "Por favor seleccione un sexo", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(this, "El número de telèfono es: "+ telefono.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "El participante ya ha sido registrado antes", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"Por favor inserte un numero de carnet valido", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "Por favor inserte el nombre", Toast.LENGTH_LONG).show();
        }

        return datosCorrectos;
    }

    public void selecionados(){
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        //---------------------------------------
        RadioButton radioButtonsi = (RadioButton) findViewById(R.id.radioButtonSi);
        RadioButton radioButtonno = (RadioButton) findViewById(R.id.radioButtonNo);
        //-----------------------------------------
        TextView textViewobsevaciones = (TextView) findViewById(R.id.textObservacion);
        EditText editTextobsevaciones = (EditText) findViewById(R.id.editObservacion);
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

        radioButtonsi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioButtonno.setChecked(false);
                    textViewobsevaciones.setVisibility(View.VISIBLE);
                    editTextobsevaciones.setVisibility(View.VISIBLE);
                }
            }
        });
        radioButtonno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioButtonsi.setChecked(false);
                    textViewobsevaciones.setVisibility(View.INVISIBLE);
                    editTextobsevaciones.setVisibility(View.INVISIBLE);
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
                if (sexo < 0 || sexo > 9) {
                    iscorrect = false;
                }

            }

            // Si todas las verificaciones son correctas
            return iscorrect;
    }

    public boolean telefono_iscorrect(String telefono){
        boolean iscorrect = false;
        if(telefono.equals("")){
            iscorrect = true;
        }else if(telefono.length() == 8){
            iscorrect = true;
        }
        return iscorrect;
    }

    //---------------------------------------------------------------------------------------------

    public void setText(String text, Person person){
        this.mensaje = text;
        this.person = person;
    }

    public void modificar(){
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);

        if(femenino.isChecked()){
            bd_manager manager = new bd_manager(ParticipanteActivity.this);
            manager.person_update(person.getCi(),nombre.getText().toString(),ci.getText().toString(),
                    femenino.getText().toString(), telefono.getText().toString());
        }else{
            bd_manager manager = new bd_manager(ParticipanteActivity.this);
            manager.person_update(person.getCi(),nombre.getText().toString(),ci.getText().toString(),
                    masculino.getText().toString(), telefono.getText().toString());
        }


    }

    public void llenar_datos(){
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);

        nombre.setText(person.getName());
        ci.setText(person.getCi());
        telefono.setText(person.getTelefono());
        if(person.getSexo().equals("Femenino")){
            femenino.setChecked(true);
        }else {
            masculino.setChecked(true);
        }
    }


    public void isnotEnable(){
        EditText editTextEdad = (EditText) findViewById(R.id.editTextNumberEdad);
        EditText editTextCalzado = (EditText) findViewById(R.id.editTextNumberCalzado);
        EditText editTextMedicion = (EditText) findViewById(R.id.editTextNumberMedicion);
        EditText editTextPierna = (EditText) findViewById(R.id.editTextNumberPierna);
        EditText editTextAltura = (EditText) findViewById(R.id.editTextNumberAltura);
        EditText editTextObservacion = (EditText) findViewById(R.id.editObservacion);
        RadioButton radioButtonsi = (RadioButton) findViewById(R.id.radioButtonSi);
        RadioButton radioButtonno = (RadioButton) findViewById(R.id.radioButtonNo);

        editTextEdad.setEnabled(false);
        editTextCalzado.setEnabled(false);
        editTextMedicion.setEnabled(false);
        editTextPierna.setEnabled(false);
        editTextAltura.setEnabled(false);
        editTextObservacion.setEnabled(false);
        radioButtonsi.setEnabled(false);
        radioButtonno.setEnabled(false);
    }

    public boolean datos_modificados_correctos(){
        boolean datosCorrectos = false;

        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText ci = (EditText) findViewById(R.id.editTextCI);
        EditText telefono = (EditText) findViewById(R.id.editTextTelefono);
        RadioButton femenino = (RadioButton) findViewById(R.id.radioButtonfemenino);
        RadioButton masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);

        if(!nombre.getText().toString().equals("") ){
            if(ci_iscorrect(ci.getText().toString())){
                if(telefono_iscorrect(telefono.getText().toString())){
                    if(femenino.isChecked()|| masculino.isChecked()){
                        datosCorrectos = true;
                    }else{
                        Toast.makeText(this, "Por favor seleccione un sexo", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "El número de telèfono es: "+ telefono.getText().toString(), Toast.LENGTH_LONG).show();
                }
                }else {
                    Toast.makeText(this,"Por favor inserte un numero de carnet valido", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(this, "Por favor inserte el nombre", Toast.LENGTH_LONG).show();
            }

            return datosCorrectos;
    }}