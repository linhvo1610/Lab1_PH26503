package com.example.lab1_ph26503.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1_ph26503.database.DbHeper;
import com.example.lab1_ph26503.model.ToDoDTO;

import java.util.ArrayList;

public class ToDoDAO {
    SQLiteDatabase database;
    DbHeper dbhelper;

    public ToDoDAO(Context context) {
        dbhelper = new DbHeper(context);
    }

    public void open() {
        database = dbhelper.getWritableDatabase();

    }

    public void close() {
        dbhelper.close();
    }

    public long Addnew(ToDoDTO toDoDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoDTO.COL_NAME_title, toDoDTO.getTitle());
        contentValues.put(ToDoDTO.COL_NAME_content, toDoDTO.getContent());
        contentValues.put(ToDoDTO.COL_NAME_date, toDoDTO.getDate());
        contentValues.put(ToDoDTO.COL_NAME_type, toDoDTO.getType());
        contentValues.put(ToDoDTO.COL_NAME_status, 0);
        long res = database.insert(toDoDTO.TB_NAME, null, contentValues);
        return res;

    }

    public int Update(ToDoDTO toDoDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoDTO.COL_NAME_title, toDoDTO.getTitle());
        contentValues.put(ToDoDTO.COL_NAME_content, toDoDTO.getContent());
        contentValues.put(ToDoDTO.COL_NAME_date, toDoDTO.getDate());
        contentValues.put(ToDoDTO.COL_NAME_type, toDoDTO.getType());
        contentValues.put(ToDoDTO.COL_NAME_status, 0);
        int res = database.update(toDoDTO.TB_NAME, contentValues, "id = " + toDoDTO.getId(), null);
        return res;
    }

    public int Delete(ToDoDTO toDoDTO) {
        return database.delete(toDoDTO.TB_NAME, "id = " + toDoDTO.getId(), null);
    }

    public boolean updateStatusToDO(Integer id, boolean check) {
        int statusValue = check ? 0 : 1;
        ContentValues values = new ContentValues();
        values.put(ToDoDTO.COL_NAME_status, statusValue);
        long row = database.update(ToDoDTO.TB_NAME, values, "id = ?",new String[]{String.valueOf(id)});
        return  row != -1;
    }

    public ArrayList<ToDoDTO> GetAll() {
        ArrayList<ToDoDTO> list_todo = new ArrayList<ToDoDTO>();
        String[] list_all = new String[]{ToDoDTO.COL_NAME_ID, ToDoDTO.COL_NAME_title, ToDoDTO.COL_NAME_content, ToDoDTO.COL_NAME_date, ToDoDTO.COL_NAME_type, ToDoDTO.COL_NAME_status};
        Cursor cursor = database.query(ToDoDTO.TB_NAME, list_all, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String date = cursor.getString(3);
            String type = cursor.getString(4);
            int status = cursor.getInt(5);
            ToDoDTO toDoDTO = new ToDoDTO();
            toDoDTO.setId(id);
            toDoDTO.setTitle(title);
            toDoDTO.setContent(content);
            toDoDTO.setDate(date);
            toDoDTO.setType(type);
            toDoDTO.setStatus(status);
            list_todo.add(toDoDTO);
            cursor.moveToNext();

        }
        return list_todo;

    }
}
