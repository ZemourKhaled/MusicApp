package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static final int SPLASH_TIME_OUT = 3500;

    Handler Splash_Handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout menu_mainActivity = findViewById(R.id.Menu_mainActivity);

        //Thread
        Splash_Handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                menu_mainActivity.setVisibility(View.VISIBLE);
            }
        }, SPLASH_TIME_OUT);
    }

    public void btn_menu_exercice(View view){

        Animation slideOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out);

        view.startAnimation(slideOut);

        view.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, ExerciceActivity.class));
            }
        }, slideOut.getDuration()-250);

    }

    public void btn_menu_parametre(View view) {

        Animation slideOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out);

        view.startAnimation(slideOut);

        view.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, ParametreActivity.class));
            }
        }, slideOut.getDuration()-250);



    }

    public void btn_menu_cours(View view){

        Animation slideOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out);

        view.startAnimation(slideOut);

        view.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, CoursActivity.class));
            }
        }, slideOut.getDuration()-250);

    }


}
