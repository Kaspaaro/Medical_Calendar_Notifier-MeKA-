package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 *
 * @author Kaspar Tullus
 * @author Semen Morozov
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void paivakirjabtn(View view) {
        Intent intent = new Intent(this, paivaKirja_kirjaaminen.class);
        startActivity(intent);
    }
    public void btn_paivakirjadata(View view) {
        Intent intent = new Intent(this, PaivaKirjaData_Displayer.class);
        startActivity(intent);
    }

    public void muistutus_btn (View view){
        Intent intent = new Intent(this, MekaMuistutus.class);
        startActivity(intent);
    }
    public void btn_poistaMuistutus (View view){
        Intent intent = new Intent(this, Calendar_memory_list.class);
        startActivity(intent);
    }


}