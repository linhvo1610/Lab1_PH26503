package com.example.lab1_ph26503.model;

public class ToDoDTO {
    int id;
    String title;
    String content;
    String date;
    String type;
    int status;

    public static final String TB_NAME ="todo";
    public static final String COL_NAME_ID ="id";
    public static final String COL_NAME_title ="title";
    public static final String COL_NAME_content ="content";
    public static final String COL_NAME_date ="date";
    public static final String COL_NAME_type ="type";
    public static final String COL_NAME_status ="status";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
