package com.example.todolist.ui;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todolist.data.Note;
import com.example.todolist.viewModel.NoteViewModel;
import com.example.todolist.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  ActivityMainBinding bind;
  private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind=ActivityMainBinding.inflate(getLayoutInflater());
        noteViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).
                get(NoteViewModel.class);
        setContentView(bind.getRoot());
        bind.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,InsertActivity.class);
                startActivityForResult(intent,1);


            }
        });
        bind.recycelView.setLayoutManager(new LinearLayoutManager(this));
        bind.recycelView.setHasFixedSize(true);
        Adapter adapter=new Adapter();
        bind.recycelView.setAdapter(adapter);
        noteViewModel.getAllData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(bind.recycelView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String ti=data.getStringExtra("title");
            String dis=data.getStringExtra("discraption");
            Note note=new Note(ti,dis);
            noteViewModel.insert(note);
            Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
        }
    }
}