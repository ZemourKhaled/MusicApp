package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParametreActivity extends AppCompatActivity {

    private ArrayList<String> selectedItems=new ArrayList<>();
    private int cpt_intervalles_sel = 0;
    private String[] noms_intervalles;
    public static boolean[] selection_intervalles;

    //todo nettoyer code
    //todo revoir interface
    //todo ajouter bouton instrument


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        //récupération des noms d'intervalles
        noms_intervalles = getResources().getStringArray(R.array.noms_intervalles);
        //gestion de la checkliste des intervalles
        ListView liste_intervalles = findViewById(R.id.liste_intervalles);
        liste_intervalles.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter_intervalles = new ArrayAdapter<>(this,R.layout.row_template,R.id.checklist_template_parametre,noms_intervalles);
        liste_intervalles.setAdapter(adapter_intervalles);
        liste_intervalles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=((TextView)view).getText().toString();
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else{
                    selectedItems.add(selectedItem);
                }
            }
        });
        //checker la sélection d'intervalles en mémoire
        for (int ind=0; ind<selection_intervalles.length; ind++){
            if (selection_intervalles[ind]){
                liste_intervalles.setItemChecked(ind,true);
                selectedItems.add(noms_intervalles[ind]);
            }
        }
    }


    public void btn_intervalles (View view) {
        //afficher la checklist des intervalles
        View liste_intervalles = findViewById(R.id.liste_intervalles);
        if (liste_intervalles.getVisibility()==View.VISIBLE){
            liste_intervalles.setVisibility(View.GONE);
        }
        else {
            liste_intervalles.setVisibility(View.VISIBLE);
        }
    }

    public void btn_save (View view) {
        // todo finir la fonction
        //sauvegarder les options
        //erreur sélection intervalles
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        CharSequence text = getResources().getText(R.string.erreur_selection_parametres);
        if (selectedItems.size() < 2) {
            Toast toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
            toast.show();
        }else {
            //sauvegarde intervalles
            boolean[] selection = new boolean[noms_intervalles.length];
            for (int ind=0;ind<selection.length;ind++) {
                if (selectedItems.contains(noms_intervalles[ind])) {
                    editor.putBoolean(noms_intervalles[ind], true);
                } else {
                    editor.putBoolean(noms_intervalles[ind], false);
                }
            }
            editor.apply();
        }
        ParametreActivity.charger_preferences(this);
    }

    public static void charger_preferences (Context context) {
        String[] noms_intervalles = context.getResources().getStringArray(R.array.noms_intervalles);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        ParametreActivity.selection_intervalles = new boolean[noms_intervalles.length];
        for (int ind=0;ind<selection_intervalles.length;ind++){
            selection_intervalles[ind] = preferences.getBoolean(noms_intervalles[ind],true);
        }
    }
}
