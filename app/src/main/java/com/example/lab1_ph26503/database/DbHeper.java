package com.example.lab1_ph26503.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHeper extends SQLiteOpenHelper {
    public static final String Db_name = "Todo_Management";

    public DbHeper(Context context) {


        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_todo = "create table todo(id integer primary key autoincrement,title text,content text,date text, type text,status integer)";
        sqLiteDatabase.execSQL(tb_todo);
            String data ="insert into todo values(0,'Abc','xyz','27/6/2023','Dễ',1)";
        sqLiteDatabase.execSQL(data);
    }
//được gọi khi nâng cấp version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!=i1){
          sqLiteDatabase.execSQL("DROP table IF EXISTS todo");
          onCreate(sqLiteDatabase);
        }
    }
}
