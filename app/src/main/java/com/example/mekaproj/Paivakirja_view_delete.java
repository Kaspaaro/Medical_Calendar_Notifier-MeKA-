package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class Paivakirja_view_delete extends AppCompatActivity {

    ArrayAdapter PaivakirjaArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_view_delete);
    }

    public void btn_Delete_View(View view) {

        Intent intent = new Intent(this, PaivaKirjaData_Displayer.class);
        startActivity(intent);
    }
}