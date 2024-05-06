package com.example.base_dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class bd_manager  {

    private bd_connection connection;
    private SQLiteDatabase database;
    private final Context mycontext;
    /// Tabla usuario
    private static final String Table_user="usuario";
    private static final String User_id="id";
    private static final String User_name="nombre";
    private static final String User_password= "contrasenna";

   ///Tabla participante
    private static final String Table_person = "participante";
    private static final String Person_id = "id";
    private static final String Person_name = "nombre";
    private static final String Person_ci = "ci";
    private static final String Person_sex = "sexo";
    private static final String Person_phone = "telefono";

    //tabla dato-participante
    private static final String Table_data_person = "datoparticipante";
    private static final String data_id  = "id";
    private static final String id_person = "idparticipante";
    private static final String data_date = "fecha";
    private static final String data_hour = "hora";
    private static final String data_age = "edad";
    private static final String data_patologia = "patologia";
    private static final String data_shoes = "numerodecalzado";
    private static final String data_medicion = "medicioncinturatobillo";
    private static final String data_long_leg = "largopierna";
    private static final String data_height = "alturadelsensor";
    private static final String data_observation = "observacion";

    //Tabla señales
    private static final String Table_sennal = "sennal";
    private static final String sennal_id = "id";
    private static final String sennal_participante = "idparticipante";
    private static final String sennal_acelX = "acelerometroX";
    private static final String sennal_acelY = "acelerometroY";
    private static final String sennal_acelZ = "acelerometroZ";
    private static final String sennal_girosX = "giroscopioX";
    private static final String sennal_girosY = "giroscopioY";
    private static final String sennal_girosZ = "giroscopioZ";
    private static final String sennal_magneX = "magnetometroX";
    private static final String sennal_magneY = "magnetometroY";
    private static final String sennal_magneZ = "magnetometroZ";
    private static final String sennal_temp = "temperatura";



    //conexión
    public bd_manager(Context context) {
        connection = new bd_connection(context);
        connection.open();
        mycontext = context;
    }

    public bd_manager open_to_write() throws SQLException {
        database = connection.getWritableDatabase();
        return this;
    }
    public bd_manager open_to_read() throws SQLException {
        database = connection.getReadableDatabase();
        return this;
    }

    public void close(){
        connection.close();
    }

    /* -----------------------------------------------------------------------
---------------------------------------------------------------------------
    CRUD de usuario
    -----------------------------------------------------------------------
 --------------------------------------------------------------------------
    */
    public void user_insert(String nombre, String pass){
        ContentValues values = new ContentValues();
        this.open_to_write();
        values.put(User_name, nombre);
        values.put(User_password,pass);
        database.insert(Table_user,null,values);
        this.close();
    }

    public ArrayList<String> user_list() {
        ArrayList<String> list = new ArrayList<String>();
        int name = -1;
        int pass = -1;
        int cant = -1;

        this.open_to_read();
        Cursor result = database.rawQuery("Select * FROM usuario", null);
        if (result != null && result.getCount() >0 ) {
            result.moveToFirst();
            try {
                do {
                    name = result.getColumnIndex(User_name);
                    pass = result.getColumnIndex(User_password);
                    String nombre = result.getString(name);
                    String passw = result.getString(pass);
                    list.add(nombre);
                    list.add(passw);
                } while (result.moveToNext());

            } finally {
                result.close();
                this.close();
            }
        }
        return list;
    }

    public void user_delete(String name){
        int colmdelete= 0;
        String whereClause = User_id + " = ?";
        int id = user_getID(name);
        String [] whereArgs = new String[]{String.valueOf(id)};
        this.open_to_write();
        colmdelete = database.delete(Table_user,whereClause,whereArgs);
        this.close();
    }

    public void user_update(String last_name, String last_pass, String new_name, String new_pass){
        int id = user_getID(last_name);
        String whereClause = User_id + " = ?";
        String [] whereArgs = new String[]{String.valueOf(id)};
        ContentValues values = new ContentValues();
        this.open_to_write();
        if(new_name == null){
            values.put(User_name, last_name);
            values.put(User_password,new_pass);
        }else if(new_pass == null){
            values.put(User_name, new_name);
            values.put(User_password,last_pass);
        }else{
            values.put(User_name, new_name);
            values.put(User_password,new_pass);
        }
        database.update(Table_user,values,whereClause,whereArgs);
        this.close();
    }

    public void user_deleteAll(){
        int colmdelete= 0;
        this.open_to_write();
        colmdelete = database.delete(Table_user,String.valueOf(1),null);
        this.close();
    }

    public int user_getID(String name){
        int id=-1;
        this.open_to_read();
        String [] whereArgs = new String[]{name};
        Cursor result = database.rawQuery("Select id from usuario where nombre = " + '"' +name + '"',null);

        if(result.moveToFirst()){
            do {
                int col= result.getColumnIndex(User_id);
                id = result.getInt(col);
            } while (result.moveToNext());
        }
        result.close();
        this.close();
        return id;
    }
/* -----------------------------------------------------------------------
---------------------------------------------------------------------------
    CRUD de participante
    -----------------------------------------------------------------------
 --------------------------------------------------------------------------
    */
    public void person_insert(String nombre, String ci,String sexo, String telefono){
        ContentValues values = new ContentValues();
        this.open_to_write();
        values.put(Person_name, nombre);
        values.put(Person_ci,ci);
        values.put(Person_sex,sexo);
        values.put(Person_phone,telefono);
        database.insert(Table_person,null,values);
        this.close();
    }

    public void person_update(String nombre, String ci, String sexo, String telefono){
        int id = person_getID(ci);
        String whereClause = Person_id + " = ?";
        String [] whereArgs = new String[]{String.valueOf(id)};
        ContentValues values = new ContentValues();

            values.put(Person_name, nombre);
            values.put(Person_ci,ci);
            values.put(Person_sex, sexo);
            values.put(Person_phone,telefono);

        this.open_to_write();
         database.update(Table_person,values,whereClause,whereArgs);
         this.close();
    }

    public void person_delete(String ci){
        int colmdelete= 0;
        String whereClause = Person_id + " = ?";
        int id = person_getID(ci);
        String [] whereArgs = new String[]{String.valueOf(id)};
        this.open_to_write();
        colmdelete = database.delete(Table_person,whereClause,whereArgs);
        this.close();
    }

    public void person_deleteAll(){
        int colmdelete= 0;
        this.open_to_write();
        colmdelete = database.delete(Table_person,String.valueOf(1),null);
        this.close();
    }

    public ArrayList<String> person_list() {
        ArrayList<String> list = new ArrayList<String>();
        int name = -1;
        int ci = -1;
        int sexo = -1;
        int telef = -1;
        int cant = -1;

        this.open_to_read();
        Cursor result = database.rawQuery("Select * FROM participante" , null);
        if (result != null && result.getCount() >0 ) {
            result.moveToFirst();
            try {
                do {
                    name = result.getColumnIndex(Person_name);
                    ci = result.getColumnIndex(Person_ci);
                    sexo = result.getColumnIndex(Person_sex);
                    telef = result.getColumnIndex(Person_phone);

                    String p_nombre = result.getString(name);
                    String p_ci = result.getString(ci);
                    String p_sexo = result.getString(sexo);
                    String p_telef = result.getString(telef);
                    list.add(p_nombre);
                    list.add(p_ci);
                    list.add(p_sexo);
                    list.add(p_telef);
                } while (result.moveToNext());

            } finally {
                result.close();
                this.close();
            }
        }
        return list;
    }

    public int person_getID(String ci_person){
        int id=-1;
        this.open_to_read();
        String [] whereArgs = new String[]{ci_person};
        Cursor result = database.rawQuery("Select id from  participante where ci =   "  + '"' +ci_person + '"',null);

        if(result.moveToFirst()){
           // do {
                int col= result.getColumnIndex(Person_id);
                id = result.getInt(col);
           // } while (result.moveToNext());
        }
        result.close();
        this.close();

        return id;
    }

    /* -----------------------------------------------------------------------
---------------------------------------------------------------------------
    CRUD de dato-participante
    -----------------------------------------------------------------------
 --------------------------------------------------------------------------
    */

   public void data_insert(String ci, int edad, int patologia, double calzado, double cintura_tobillo,
                            double largo_pierna, double altura_sensor, String obsercion){
        ContentValues values = new ContentValues();
        values.put(id_person, person_getID(ci));
        values.put(data_date,getDate());
        values.put(data_hour,gethour());
        values.put(data_age,edad);
        values.put(data_patologia,patologia);
        values.put(data_shoes,calzado);
        values.put(data_medicion,cintura_tobillo);
        values.put(data_long_leg,largo_pierna);
        values.put(data_height,altura_sensor);
        values.put(data_observation,obsercion);
        this.open_to_write();
        database.insert(Table_data_person,null,values);
        this.close();
    }

    public void data_update(String ci, String fecha, int edad, int patologia, double calzado, double cintura_tobillo,
                            double largo_pierna, double altura_sensor, String obsercion){
        int id = data_getID(ci,fecha);
        String whereClause = data_id + " = ?";
        String [] whereArgs = new String[]{String.valueOf(id)};
        ContentValues values = new ContentValues();

        values.put(id_person, person_getID(ci));
        values.put(data_age,edad);
        values.put(data_patologia,patologia);
        values.put(data_shoes,calzado);
        values.put(data_medicion,cintura_tobillo);
        values.put(data_long_leg,largo_pierna);
        values.put(data_height,altura_sensor);
        values.put(data_observation,obsercion);

        this.open_to_write();
        database.update(Table_data_person,values,whereClause,whereArgs);
        this.close();
    }

    public void data_delete(String ci, String fecha){
        int colmdelete= 0;
        String whereClause = data_id + " = ?";
        int id = data_getID(ci,fecha);
        String [] whereArgs = new String[]{String.valueOf(id)};
        this.open_to_write();
        colmdelete = database.delete(Table_data_person,whereClause,whereArgs);
        this.close();
    }

    public void data_deleteAll(){
        int colmdelete= 0;
        this.open_to_write();
        colmdelete = database.delete(Table_data_person,String.valueOf(1),null);
        this.close();
    }

    public ArrayList<String> data_list() {
        ArrayList<String> list = new ArrayList<String>();
        int person = -1;
        int date = -1;
        int hour = -1;
        int ege = -1;
        int patologia = -1;
        int shoes = -1;
        int medicion = -1;
        int leg = -1;
        int height = -1;
        int observation = -1;
        int cant = -1;

        this.open_to_read();
        Cursor result = database.rawQuery("Select * FROM datoparticipante" , null);
        if (result != null && result.getCount() >0 ) {
            result.moveToFirst();
            person = result.getColumnIndex(id_person);
            date = result.getColumnIndex(data_date);
            hour = result.getColumnIndex(data_hour);
            ege = result.getColumnIndex(data_age);
            patologia = result.getColumnIndex(data_patologia);
            shoes = result.getColumnIndex(data_shoes);
            medicion = result.getColumnIndex(data_medicion);
            leg = result.getColumnIndex(data_long_leg);
            height = result.getColumnIndex(data_height);
            observation = result.getColumnIndex(data_observation);
            try {
                do {
                    int persona = result.getInt(person);
                    String fecha = result.getString(date);
                    String hora = result.getString(hour);
                    int edad = result.getInt(ege);
                    int patol = result.getInt(patologia);
                    double calzado = result.getDouble(shoes);
                    double cintura_tobillo = result.getDouble(medicion);
                    double pierna = result.getDouble(leg);
                    double altura = result.getDouble(height);
                    String observ = result.getString(observation);

                    list.add(String.valueOf(persona));
                    list.add(fecha);
                    list.add(hora);
                    list.add(String.valueOf(edad));
                    list.add(String.valueOf(patol));
                    list.add(String.valueOf(calzado));
                    list.add(String.valueOf(cintura_tobillo));
                    list.add(String.valueOf(pierna));
                    list.add(String.valueOf(altura));
                    list.add(observ);
                } while (result.moveToNext());

            } finally {
                result.close();
                this.close();
            }
        }
        return list;
    }

    public int data_getID(String ci, String date){
        int id=-1;
        int id_part= person_getID(ci);
       // String [] whereArgs = new String[]{String.valueOf(id_part), fecha};

        this.open_to_read();
        Cursor result = database.rawQuery("Select id from datoparticipante where idparticipante = " + id_part + " and  fecha = " + '"'+date + '"',null);

        if(result.moveToFirst()){
            do {
                int col= result.getColumnIndex(data_id);
                id = result.getInt(col);
            } while (result.moveToNext());
        }
        this.close();
        result.close();
        return id;
    }

    public String getDate(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
       return dateFormat.format(date);
    }
    public String gethour(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("hh:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



   /* -----------------------------------------------------------------------
---------------------------------------------------------------------------
   CRUD de señales
    -----------------------------------------------------------------------
 --------------------------------------------------------------------------
    */
   public void sennal_insert(String ci, double acelx, double acely,double acelz,double girosx, double girosy, double girosz,
                             double magnex,double magney, double magnez,double tempe){
       ContentValues values = new ContentValues();
       values.put(sennal_participante, person_getID(ci));
       values.put(sennal_acelX,acelx);
       values.put(sennal_acelY,acely);
       values.put(sennal_acelZ,acelz);
       values.put(sennal_girosX,girosx);
       values.put(sennal_girosY,girosy);
       values.put(sennal_girosZ,girosz);
       values.put(sennal_magneX,magnex);
       values.put(sennal_magneY,magney);
       values.put(sennal_magneZ,magnez);
       values.put(sennal_temp,tempe);
       this.open_to_write();
       database.insert(Table_sennal,null,values);
       this.close();
   }
    public void sennal_deleteAll(){
        int colmdelete= 0;
        this.open_to_write();
        colmdelete = database.delete(Table_sennal,String.valueOf(1),null);
        this.close();
    }


}


