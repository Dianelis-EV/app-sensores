package com.example.caminata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Ruta por defecto
    private static String bd_path = "/data/data/com.example.caminata/databases/";
    private static final String BD_NAME = "sensores_inteligentes.db";
    private SQLiteDatabase database;
    private final Context mycontext;

    public static final String User_id= "id";
    public static final String User_name="nombre";
    public static final String User_password= "contrasenna";
    public static final String Table_user="usuario";
    private static final String[] cols = new String[]{User_name,User_password};



    public DatabaseHelper( Context context) {
        super(context, BD_NAME, null, 1);
        mycontext = context;
    }
    //Crear una base de datos una vacia en el sistema
    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(!dbexist){
            this.getReadableDatabase();
            try{
                copydatabase();
            }catch (IOException e){
                throw new Error("Error copiando Base de Datos");
            }
        }
    }
    private boolean checkdatabase(){
        SQLiteDatabase checkDB= null;
        try{
            String path= bd_path + BD_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
           checkDB = null ;
        }
        if(checkDB != null){
            checkDB.close();

        }
        return checkDB!= null? true : false;
    }

    public void copydatabase() throws IOException {
        InputStream myImput= mycontext.getAssets().open(BD_NAME);
        String outfilename = bd_path + BD_NAME;
        OutputStream myoutput = new FileOutputStream(outfilename);
        byte[] buffer = new byte[20000];
        int length;
        while ((length = myImput.read(buffer))>0){
            myoutput.write(buffer,0,length);
        }
        myoutput.flush();
        myoutput.close();
        myImput.close();
    }

    public void open(){
        try{
            createdatabase();
        } catch (IOException e) {
            throw new Error("Ha sido imposible crear la base de datos");
        }
        String myPath = bd_path + BD_NAME;
        database = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void user_insert(String nombre, String pass){
        ContentValues values = new ContentValues();
        this.open();
        values.put(User_name, nombre);
        values.put(User_password,pass);
        database.insert(Table_user,null,values);
        Cursor result= database.rawQuery("Select * FROM usuario",null);
        result.close();
        this.close();
    }

    public  ArrayList<String>  user_list() {
        ArrayList<String> list = new ArrayList<String>();
        int name = -1;
        int pass = -1;
        int cant = -1;
        this.open();
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
        this.getWritableDatabase();
        colmdelete = database.delete(Table_user,whereClause,whereArgs);
        this.close();
    }

    public void user_update(String last_name, String last_pass, String new_name, String new_pass){
        int id = user_getID(last_name);
        String whereClause = User_id + " = ?";
        String [] whereArgs = new String[]{String.valueOf(id)};
        ContentValues values = new ContentValues();
        this.getWritableDatabase();
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
        this.getWritableDatabase();
        colmdelete = database.delete(Table_user,String.valueOf(1),null);
        this.close();
    }

   public int user_getID(String name){
        int id=-1;
        this.getReadableDatabase();
        String [] whereArgs = new String[]{name};
        Cursor result = database.rawQuery("Select id from usuario where nombre = " + '"' +name + '"',null);

       if(result.moveToFirst()){
           do {
               int col= result.getColumnIndex(User_id);
               id = result.getInt(col);
           } while (result.moveToNext());
       }
       this.close();
        return id;
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
