package com.example.mekaproj.PaivaKirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mekaproj.MainActivity;
import com.example.mekaproj.MekaDataBase;
import com.example.mekaproj.R;

import java.util.List;
/**
 *
 * @author Kaspar Tullus
 *
 */
public class Paivakirja_view_delete extends AppCompatActivity {
    private TextView tv_Paivakirjadatakirje;  //Kirje Textview osio mihin on kirjoitettu tarina
    private TextView tv_Paivakirjadataotsikko; //Tarinan Otsikko
    private PaivaKirjaData getpk;
    private MekaDataBase getdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_view_delete);

        //Id kentät
        tv_Paivakirjadatakirje = findViewById(R.id.viewKirje);
        tv_Paivakirjadataotsikko = findViewById(R.id.viewOtsikko);

        MekaDataBase pkdata = new MekaDataBase(Paivakirja_view_delete.this);
        List<PaivaKirjaData> arrayList = pkdata.getEverything();
        Intent intent = getIntent();
        String pos = intent.getStringExtra("POSITION");
        int position = Integer.parseInt(pos);
        PaivaKirjaData paivakirja = arrayList.get(position);
        String otsikko = paivakirja.getOtsikko();
        String kirje = paivakirja.getKirje();
        tv_Paivakirjadataotsikko.setText(otsikko);
        tv_Paivakirjadatakirje.setText(kirje);
        getpk = paivakirja;
        getdb = pkdata;
    }
    public void btn_Delete_View (View view){
        getdb.deleteOne(getpk);
        Intent intent = new Intent(Paivakirja_view_delete.this, MainActivity.class);
        startActivity(intent);
    }
}