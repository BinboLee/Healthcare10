package com.example.healthcare10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private  MyDBHelper dbHelper;
    private String TBNAME;
    private String TBNAME1;
    public Manager(Context context) {
        dbHelper=new MyDBHelper(context);
        TBNAME=MyDBHelper.TB_NAME;
        TBNAME1=MyDBHelper.TB_NAME1;
    }

    public void add(Item item){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("WEIGHT",item.getWEIGHT());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public List<Item> listAll(){
        List<Item> rateList=null;
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            rateList=new ArrayList<Item>();
            while (cursor.moveToNext()){
                Item item = new Item();
                item.setWEIGHT(cursor.getString(cursor.getColumnIndex("WEIGHT")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }
    public void deleteAll(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL("Delete from "+TBNAME+" where 1=1");
    }

    public void add1(Item1 item1){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("MEMO",item1.getMemo());
        db.insert(TBNAME1,null,values);
        db.close();
    }

    public List<Item1> listAll1(){
        List<Item1> rateList=null;
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME1,null,null,null,null,null,null);
        if(cursor!=null){
            rateList=new ArrayList<Item1>();
            while (cursor.moveToNext()){
                Item1 item1 = new Item1();
                item1.setMemo(cursor.getString(cursor.getColumnIndex("MEMO")));
                rateList.add(item1);
            }
            cursor.close();
        }
        db.close();
        return rateList;
    }
    public void delete1(String content){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL("Delete from "+TBNAME1+"  where MEMO='"+content+"'");
    }

}
