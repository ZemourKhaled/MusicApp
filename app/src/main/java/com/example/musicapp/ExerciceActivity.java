package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ExerciceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        //test
        CharSequence[] noms_intervalles = getResources().getStringArray(R.array.noms_intervalles);
        CharSequence texte;
        Toast toast;
        for (int ind=0;ind<ParametreActivity.selection_intervalles.length;ind++){
            if (ParametreActivity.selection_intervalles[ind]){
                texte=noms_intervalles[ind];
                toast=Toast.makeText(this,texte,Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
