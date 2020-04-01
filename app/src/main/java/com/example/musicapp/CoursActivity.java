package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CoursActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView noteCard, sepDemiTonCard, interCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);

        //defining cards
        noteCard = (CardView) findViewById(R.id.nom_note_card);
        sepDemiTonCard = (CardView) findViewById(R.id.sep_demi_ton);
        interCard = (CardView) findViewById(R.id.intervalle_card);

        //add click listener to the cards
        noteCard.setOnClickListener(this);
        sepDemiTonCard.setOnClickListener(this);
        interCard.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        Intent i ;

        switch (v.getId())
        {
            case R.id.nom_note_card : i =  new Intent(this, NomDesNotes.class);startActivity(i); break;
            case R.id.sep_demi_ton : i = new Intent(this, SeparationDemiTon.class);startActivity(i); break;
            case R.id.intervalle_card : i = new Intent(this, IntervalleNotes.class);startActivity(i); break;
            default:break;
        }


    }
}
