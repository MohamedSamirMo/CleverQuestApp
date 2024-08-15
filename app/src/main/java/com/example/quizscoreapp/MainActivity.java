package com.example.quizscoreapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.quizscoreapp.Adapter.CategoryAdapter;
import com.example.quizscoreapp.Models.CategoryModel;
import com.example.quizscoreapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseDatabase database;
    ArrayList<CategoryModel> list;
    CategoryAdapter adapter;
    ProgressDialog progressDialog;
    Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();

        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        if (loadingDialog.getWindow()!=null){
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loadingDialog.setCancelable(false);
        }
        loadingDialog.show();


        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.recCategory.setLayoutManager(layoutManager);

        adapter=new CategoryAdapter(this,list);
        binding.recCategory.setAdapter(adapter);

        database.getReference().child("categories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            list.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String categoryName = dataSnapshot.child("categoryName").getValue(String.class);
                                String categoryImage = dataSnapshot.child("categoryImage").getValue(String.class);
                                String key = dataSnapshot.getKey();
                                Integer setNumber = dataSnapshot.child("setNumber").getValue(Integer.class);

                                // Check if any of the values are null and handle accordingly
                                if (categoryName != null && categoryImage != null && setNumber != null) {
                                    list.add(new CategoryModel(categoryName, categoryImage, key, setNumber));

                                } else {
                                    Toast.makeText(MainActivity.this, "Some data is missing or incorrect.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No categories exist.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }
                });


    }
}