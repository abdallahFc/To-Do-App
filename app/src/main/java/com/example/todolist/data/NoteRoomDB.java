package com.example.todolist.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,version = 1)
public abstract class NoteRoomDB extends RoomDatabase {
    private static NoteRoomDB instance;
    public abstract NoteDao dao();
    //Singlton
    public static synchronized NoteRoomDB getInstante(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDB.class,"note-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }


}
