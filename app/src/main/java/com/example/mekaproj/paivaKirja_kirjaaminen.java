package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class paivaKirja_kirjaaminen extends AppCompatActivity {

    //Nappi refrensit ja muut layoutin controls
    Button btnTallennaPK;
    EditText editTextKirje,editTextOtsikko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_kirja_kirjaaminen);

        //Napit + Tekstikentät.
        btnTallennaPK = findViewById(R.id.btnTallennaPK);
        editTextKirje = findViewById(R.id.editTextKirje);
        editTextOtsikko = findViewById(R.id.editTextOtsikko);

        ///Button Listeners
        btnTallennaPK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ///Päivä kirjan datan kirjaaminen
                PaivaKirjaData paivaKirjaData;

                // Tässä testataan ensiksi että menikö arvot päiväkirjadataan tai ei, jos ei mennyt se palautta Catchin eli errorin.
                try{
                    paivaKirjaData = new PaivaKirjaData(editTextOtsikko.getText().toString(),editTextKirje.getText().toString());

                    Toast.makeText(paivaKirja_kirjaaminen.this,"Tallennettu",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(paivaKirja_kirjaaminen.this,"Paivakirjan tekemine epäonnistui",Toast.LENGTH_SHORT).show();

                    paivaKirjaData = new PaivaKirjaData("ERROR","ERROR");

                }

                MekaDataBase mekaDataBase = new MekaDataBase(paivaKirja_kirjaaminen.this);

                //Datan lähettäminen
                boolean success = mekaDataBase.addOne(paivaKirjaData);

                //Tulostaa toastin joka kertoo että arvot tallennettu databaseen.
                Toast.makeText(paivaKirja_kirjaaminen.this,"SUCCESS= " + success,Toast.LENGTH_SHORT).show();

                //Vie suoraan PaivaKirjaData_Displayer activitiin.
                Intent intent = new Intent(paivaKirja_kirjaaminen.this, PaivaKirjaData_Displayer.class);
                startActivity(intent);

            }

        });
    }
}