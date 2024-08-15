package com.example.quizscoreapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizscoreapp.databinding.ActivitySingInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SingInActivity extends AppCompatActivity {

    ActivitySingInBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ProgressDialog dialog;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySingInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        dialog=new ProgressDialog(SingInActivity.this);
        dialog.setTitle("Creating your account");
        dialog.setMessage("We're creating your account");

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                auth.signInWithEmailAndPassword(binding.userEmail.getText().toString(),binding.password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                           if (task.isSuccessful()) {
                               Intent intent=new Intent(SingInActivity.this,MainActivity.class);
                               startActivity(intent);
                           }
                           else {

                               Toast.makeText(SingInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                           }
                            }
                        });
            }
        });
        binding.noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SingInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        if (currentUser!=null) {
            Intent intent=new Intent(SingInActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }
}