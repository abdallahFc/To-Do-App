package com.example.todolist.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    private String title;
    private String dis;

    public int getId() {
        return id;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;


    public void setId(int id) {
        this.id=id;
    }
    public Note(String title, String dis) {
        this.title = title;
        this.dis = dis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }


}

