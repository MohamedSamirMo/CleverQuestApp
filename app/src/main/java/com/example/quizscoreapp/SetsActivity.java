package com.example.quizscoreapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizscoreapp.Adapter.GrideAdapter;
import com.example.quizscoreapp.databinding.ActivitySetsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class SetsActivity extends AppCompatActivity {

    ActivitySetsBinding binding;
    FirebaseDatabase database;
    GrideAdapter adapter;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        database= FirebaseDatabase.getInstance();
        key=getIntent().getStringExtra("key");
        adapter=new GrideAdapter(getIntent().getIntExtra("sets",0)
                ,getIntent().getStringExtra("category"));
        binding.gridView.setAdapter(adapter);


    }
}