package com.example.quizscoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DELAY = 9000; // 3 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimation);
        lottieAnimationView.setAnimation(R.raw.quiz); // Optional: set the animation programmatically

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent intent = new Intent(SplashActivity.this, SingInActivity.class);
                startActivity(intent);
                // Close the splash activity
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }
}