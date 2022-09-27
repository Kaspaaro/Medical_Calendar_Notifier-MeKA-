package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Paivakirja_view_delete extends AppCompatActivity {
    private static final String TAG = "Paivakirja_view_delete";
    private TextView tv_Paivakirjadatakirje;
    private TextView tv_Paivakirjadataotsikko;
    ArrayAdapter PaivakirjaArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_view_delete);

        //Id kentät
        tv_Paivakirjadatakirje = findViewById(R.id.viewKirje);
        tv_Paivakirjadataotsikko = findViewById(R.id.viewOtsikko);

        /*Bundle recdData = getIntent().getExtras();
        String myVal = recdData.getString("value");*/
        MekaDataBase pkdata = new MekaDataBase(Paivakirja_view_delete.this);
        List<PaivaKirjaData> arrayList = pkdata.getEverything();
        Intent intent = getIntent();
        String pos = intent.getStringExtra("POSITION");
        int position = Integer.parseInt(pos);
        PaivaKirjaData paivakirja = arrayList.get(position);
        int id = paivakirja.getID();
        String otsikko = paivakirja.getOtsikko();
        String kirje = paivakirja.getKirje();
        tv_Paivakirjadataotsikko.setText(otsikko);
        tv_Paivakirjadatakirje.setText(kirje);

    }

    public void btn_Delete_View(View view) {
        Intent intent = new Intent(this, PaivaKirjaData_Displayer.class);
        startActivity(intent);
    }
}