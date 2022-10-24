package com.example.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.databinding.ActivityInsertBinding;

public class InsertActivity extends AppCompatActivity {
    private ActivityInsertBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("title",binding.titile.getText().toString());
                intent.putExtra("discraption",binding.description.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}