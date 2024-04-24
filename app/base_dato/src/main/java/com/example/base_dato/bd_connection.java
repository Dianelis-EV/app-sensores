package com.example.base_dato;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bd_connection extends SQLiteOpenHelper {
    private static final String BD_NAME = "sensores_inteligentes.db";
    private static final int BD_Version =1;

    public bd_connection(Context context) {
        super(context, BD_NAME, null, BD_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL Code
        sqLiteDatabase.execSQL(bd_manager.Table_user_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //SQL Code
        sqLiteDatabase.execSQL(" DROP TABLE IF  EXISTS " + bd_manager.Table_user);
    }
}
