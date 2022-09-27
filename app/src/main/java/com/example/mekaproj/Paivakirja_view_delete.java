package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        if(getIntent().hasExtra("Listviewclick")){
        }

    }

    public void btn_Delete_View(View view) {
        Intent intent = new Intent(this, PaivaKirjaData_Displayer.class);
        startActivity(intent);
    }
}