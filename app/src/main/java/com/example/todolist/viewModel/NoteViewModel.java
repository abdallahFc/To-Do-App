package com.example.todolist.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.data.Note;
import com.example.todolist.repo.NoteRepo;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {
    private NoteRepo repo;
    private LiveData<List<Note>> allData;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repo=new NoteRepo(application);
        allData=repo.getAllData();
    }
    public void insert(Note note){
       repo.insertData(note);
    }
    public void delete(Note note){
        repo.deleteData(note);
    }
    public void update(Note note){
      repo.updateData(note);
    }
    public LiveData<List<Note>> getAllData(){
        return allData;
    }
}
