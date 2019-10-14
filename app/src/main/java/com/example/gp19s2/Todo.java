package com.example.gp19s2;


import java.sql.Time;
import java.util.Date;

public class Todo {

    private int eid;


    private String title;

    private String description;

    private String time;

    private String date;
    private String completed_or_not;

    public Todo(String title, String description, String date, String time, String completed_or_not) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.completed_or_not = completed_or_not;
    }

    public int getEid() {
        return eid;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getCompleted_or_not() {return completed_or_not;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void setCompleted_or_not(String completed_or_not) {
        this.completed_or_not = completed_or_not;
    }


}
