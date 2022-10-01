package com.example.mekaproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
/**
 *
 * @author Kaspar Tullus
 */
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
                    paivaKirjaData = new PaivaKirjaData(-1,editTextOtsikko.getText().toString(),editTextKirje.getText().toString());

                    Toast.makeText(paivaKirja_kirjaaminen.this,"Tallennettu",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(paivaKirja_kirjaaminen.this,"Paivakirjan tekemine epäonnistui",Toast.LENGTH_SHORT).show();

                    paivaKirjaData = new PaivaKirjaData(0,"ERROR","ERROR");

                }

                Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);                //this intent will be called once the setting alarm completes
                intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentBack);

                MekaDataBase mekaDataBase = new MekaDataBase(paivaKirja_kirjaaminen.this);

                //Datan lähettäminen
                boolean success = mekaDataBase.addOnePK(paivaKirjaData);

                //Tulostaa toastin joka kertoo että arvot tallennettu databaseen.
                Toast.makeText(paivaKirja_kirjaaminen.this,"Tallenettu",Toast.LENGTH_SHORT).show();

                //Vie suoraan PaivaKirjaData_Displayer activitiin.
                Intent intent = new Intent(paivaKirja_kirjaaminen.this, PaivaKirjaData_Displayer.class);
                startActivity(intent);


            }


        });

    }
}