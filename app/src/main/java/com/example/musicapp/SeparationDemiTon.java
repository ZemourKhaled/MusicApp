package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SeparationDemiTon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separation_demi_ton);

        TextView text = findViewById(R.id.text);

        text.setMovementMethod(new ScrollingMovementMethod());
    }
}
