package com.example.quizscoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizscoreapp.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        int correctAnswer=getIntent().getIntExtra("correctAnswer",0);
        int totalQuestion=getIntent().getIntExtra("totalQuestion",0);
        int wrongAnswer=totalQuestion-correctAnswer;
        binding.totalRight.setText(String.valueOf(correctAnswer));
        binding.totalWrong.setText(String.valueOf(wrongAnswer));
        binding.totalQuestion.setText(String.valueOf(totalQuestion));
        binding.progressBar.setProgress(totalQuestion);
        binding.progressBar.setProgress(correctAnswer);
        binding.progressBar.setProgressMax(totalQuestion);
        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScoreActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}