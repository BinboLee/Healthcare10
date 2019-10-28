package com.example.healthcare10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private  static final String DB_NAME="myhealth2.db";
    public  static final String TB_NAME="tb_health";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    public MyDBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TB_NAME +"(WEIGHT TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}