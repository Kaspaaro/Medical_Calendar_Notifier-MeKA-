package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class PaivaKirjaData_Displayer extends AppCompatActivity {

    private ListView lv_Paivakirjadata;
    ArrayAdapter PaivakirjaArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_kirja_data_displayer);

        //Tämä Näyttää Tallennetut rivit databasesta view listalle
        lv_Paivakirjadata = findViewById(R.id.pkDATADISPLAY);

        MekaDataBase mekaDataBase = new MekaDataBase(PaivaKirjaData_Displayer.this);

        List<PaivaKirjaData> everything = mekaDataBase.getEverything();

        // Kirjoitaa arvot viewlistalle databasesta.
        PaivakirjaArrayAdapter = new ArrayAdapter<PaivaKirjaData>(PaivaKirjaData_Displayer.this, android.R.layout.simple_list_item_1, everything);

        lv_Paivakirjadata.setAdapter(PaivakirjaArrayAdapter);
    }

}