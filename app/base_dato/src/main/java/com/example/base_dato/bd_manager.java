package com.example.base_dato;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class bd_manager  {

    private bd_connection connection;
    private SQLiteDatabase basededato;
    /// Tabla usuario
    public static final String Table_user="usuario";
    public static final String User_id="id";
    public static final String User_password= "contrasenna";

    public static final String Table_user_create= "create table usuario(id interger not null PRIMARY kEY AUTOINCREMENT, nombre text not null, contrasenna text not null);";

    //conexión
    public bd_manager(Context context) {
        connection = new bd_connection(context);
    }

    public bd_manager open() throws SQLException {
        basededato = connection.getWritableDatabase();
        return this;
    }

    public void close(){
        connection.close();
    }
}
