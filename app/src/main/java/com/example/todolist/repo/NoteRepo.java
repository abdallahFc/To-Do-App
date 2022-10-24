package com.example.todolist.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist.data.Note;
import com.example.todolist.data.NoteDao;
import com.example.todolist.data.NoteRoomDB;

import java.util.List;

public class NoteRepo {
    private NoteDao dao;
    private LiveData<List<Note>> allData;

    public NoteRepo(Application app){
        NoteRoomDB db=NoteRoomDB.getInstante(app);
        dao= db.dao();
        allData=dao.getAll();
    }
    //insert 
    public void insertData(Note note){
        new InsertTask(dao).execute(note);
    }
    public void deleteData(Note note){
        new DeleteTask(dao).execute(note);
    }
    public void updateData(Note note){
        new UpdateTask(dao).execute(note);
    }
    public LiveData<List<Note>> getAllData(){
        return allData;
    }
    public static class InsertTask extends AsyncTask<Note,Void,Void>{
        private NoteDao dao;

        public InsertTask(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.insert(notes[0]);
            return null;
        }
    }
    public static class DeleteTask extends AsyncTask<Note,Void,Void>{
        private NoteDao dao;

        public DeleteTask(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.delete(notes[0]);
            return null;
        }
    }
    public static class UpdateTask extends AsyncTask<Note,Void,Void>{
        private NoteDao dao;

        public UpdateTask(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.update(notes[0]);
            return null;
        }
    }

    
}
