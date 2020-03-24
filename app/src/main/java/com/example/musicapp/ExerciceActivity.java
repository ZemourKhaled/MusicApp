package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ExerciceActivity extends AppCompatActivity {

    private MediaPlayer first_sound,second_sound;
    private int premier_son;
    private int deuxieme_son;
    public static final int NOMBRE_DE_SONS = 82;
    public static final int NOMBRE_DE_REPONSES = 6;
    private ImageView image_play_button;
    private TextView[] reponses;
    private String nom_intervalle_bonne_reponse;
    private CardView[] reponse_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        //test
        /*CharSequence[] noms_intervalles = getResources().getStringArray(R.array.noms_intervalles);
        CharSequence texte;
        Toast toast;
        for (int ind=0;ind<ParametreActivity.selection_intervalles.length;ind++){
            if (ParametreActivity.selection_intervalles[ind]){
                texte=noms_intervalles[ind];
                toast=Toast.makeText(this,texte,Toast.LENGTH_SHORT);
                toast.show();
            }
        }*/

        String[] noms_intervalles;
        int[] intervalles_choisie;
        int cpt = 0;
        int i;

        noms_intervalles  = getResources().getStringArray(R.array.noms_intervalles);
        this.image_play_button = findViewById(R.id.image_play_button);
        this.reponses = new TextView[NOMBRE_DE_REPONSES];
        this.reponses[0]= findViewById(R.id.reponse0);
        this.reponses[1]= findViewById(R.id.reponse1);
        this.reponses[2]= findViewById(R.id.reponse2);
        this.reponses[3]= findViewById(R.id.reponse3);
        this.reponses[4]= findViewById(R.id.reponse4);
        this.reponses[5]= findViewById(R.id.reponse5);

        this.reponse_card = new CardView[NOMBRE_DE_REPONSES];
        this.reponse_card[0]= findViewById(R.id.card0);
        this.reponse_card[1]= findViewById(R.id.card1);
        this.reponse_card[2]= findViewById(R.id.card2);
        this.reponse_card[3]= findViewById(R.id.card3);
        this.reponse_card[4]= findViewById(R.id.card4);
        this.reponse_card[5]= findViewById(R.id.card5);

        //calculer le nombre d'intervalle sélectionné
        for(i=0; i< ParametreActivity.selection_intervalles.length;i++){
            if (ParametreActivity.selection_intervalles[i]){
                cpt = cpt+1;
            }
        }

        intervalles_choisie = new int[cpt];

        cpt = 0;
        //remplire le tableau des intervalles choisie
        for(i=0; i< ParametreActivity.selection_intervalles.length;i++){
            if (ParametreActivity.selection_intervalles[i]){
                intervalles_choisie[cpt] = i;
                cpt = cpt+1;
            }
        }

        int random_intervalle = new Random().nextInt(intervalles_choisie.length);
        //intervalles_choisie[random_intervalle] est la bonne réponse
        int random_son =  new Random().nextInt(ExerciceActivity.NOMBRE_DE_SONS - intervalles_choisie[random_intervalle]);

        this.premier_son = random_son;
        this.deuxieme_son = random_son + intervalles_choisie[random_intervalle];


        int random_reponse_place = new Random().nextInt(NOMBRE_DE_REPONSES);
        this.nom_intervalle_bonne_reponse = noms_intervalles[intervalles_choisie[random_intervalle]];
        reponses[random_reponse_place].setText(this.nom_intervalle_bonne_reponse);


        //remplire les autres réponses

        int random_autres_reponses;
        boolean reponse_accepter, reponse_existe;
        int j;


        for (i=0; i < reponses.length; i++){
            reponse_accepter = false;
            if(i != random_reponse_place){
                while(!reponse_accepter){
                    random_autres_reponses = new Random().nextInt(noms_intervalles.length);
                    reponse_existe = false;
                    for(j=0; j<reponses.length;j++){
                        if (reponses[j].getText().toString().equals(noms_intervalles[random_autres_reponses])){
                            reponse_existe = true;
                        }
                    }
                    if (!reponse_existe){
                        reponse_accepter = true;
                        reponses[i].setText(noms_intervalles[random_autres_reponses]);
                    }
                }

            }
        }


    }

    public void reponseButton(View view){
        int i;

        for(i=0; i<reponse_card.length; i++){
            reponse_card[i].setCardBackgroundColor(getResources().getColor(R.color.white));
        }

        CardView card_view_clicked = (CardView) view;
        TextView text_view = (TextView) card_view_clicked.getChildAt(0);

        if (!text_view.getText().toString().equals(nom_intervalle_bonne_reponse)){
            card_view_clicked.setCardBackgroundColor(getResources().getColor(R.color.red));
        }else{
            card_view_clicked.setCardBackgroundColor(getResources().getColor(R.color.green));
            finish();
            startActivity(getIntent());
        }
    }


    public void suivantButton(View view){
        finish();
        startActivity(getIntent());
    }


    public void playButton(View view){

        if(this.first_sound == null){
            this.first_sound = MediaPlayer.create(this, getResources().getIdentifier("piano"+this.premier_son,"raw", getPackageName()));
            this.first_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    startSecoundSound();
                    releaseFirstSound();
                }
            });
        }else{
            this.first_sound.pause();
        }

        if (this.second_sound == null){
            this.second_sound = MediaPlayer.create(this, getResources().getIdentifier("piano"+this.deuxieme_son, "raw", getPackageName()));
            this.second_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseSecoundSound();
                    image_play_button.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }
            });
        }else{
            this.second_sound.pause();
        }

        this.image_play_button.setImageResource(R.drawable.ic_pause_black_24dp);
        startFirstSound();

    }

    private void startFirstSound(){
        if (this.first_sound != null){
            this.first_sound.start();
        }
    }

    private void startSecoundSound(){
        if (this.second_sound != null){
            this.second_sound.start();
        }
    }

    private void releaseFirstSound(){
        if (this.first_sound != null){
            this.first_sound.release();
            this.first_sound = null;
        }
    }

    private void releaseSecoundSound(){
        if (this.second_sound != null){
            this.second_sound.release();
            this.second_sound = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("premier_son", premier_son);
        outState.putInt("deuxieme_son", deuxieme_son);


        for (int i=0; i<reponses.length; i++){
            outState.putString("reponse"+i, reponses[i].getText().toString());
        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        premier_son = savedInstanceState.getInt("premier_son");
        deuxieme_son = savedInstanceState.getInt("deuxieme_son");


        for(int i=0; i<reponses.length;i++){
            reponses[i].setText(savedInstanceState.getString("reponse"+i));
        }
    }
}
