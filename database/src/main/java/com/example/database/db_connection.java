package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class db_connection extends SQLiteOpenHelper {
    private static final String BD_NAME = "sensores_inteligentes.db";
    private static String bd_path = "/data/data/com.example.caminata/databases/";
    private static final int BD_Version =1;
    private SQLiteDatabase database;
    private final Context mycontext;


    public db_connection(Context context) {
        super(context, BD_NAME, null, BD_Version);
        mycontext = context;

    }
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
        byte[] buffer = new byte[1024];
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
        database = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL Code
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
