package com.example.gp19s2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Date;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="todo.db";
    public static final String TABLE_NAME="todo_table";
    public static final String COL_1="ID";
    public static final String COL_2="TITLE";
    public static final String COL_3="DATE";
    public static final String COL_4="TIME";
    public static final String COL_5="DESCRIPTION";
    public static final String COL_6="COMPLETED";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATE DATE,TIME TIME,DESCRIPTION TEXT,COMPLETED TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insert(String title,String date,String time, String DES, String completed ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, time);
        contentValues.put(COL_5, DES);
        contentValues.put(COL_6, completed);
        long result=db.insert(TABLE_NAME,null,contentValues);
        db.close();
        if (result!=-1){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor getList(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}

