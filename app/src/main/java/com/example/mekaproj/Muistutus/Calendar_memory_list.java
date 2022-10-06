package com.example.mekaproj.Muistutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mekaproj.MekaDataBase;
import com.example.mekaproj.R;

import java.util.List;

/**
 *
 * @author Kaspar Tullus
 */
public class Calendar_memory_list extends AppCompatActivity {
    private ListView lv_Muistutusdata;
    ArrayAdapter MuistiArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_memory_list);

        //Tämä Näyttää Tallennetut rivit databasesta view listalle
        lv_Muistutusdata = findViewById(R.id.list_Memo_Calendar);
        MekaDataBase mekaDataBase = new MekaDataBase(Calendar_memory_list.this);

        List<MuistutusData> everything = mekaDataBase.getMuitsAll();

        MuistiArrayAdapter = new ArrayAdapter<>(Calendar_memory_list.this, android.R.layout.simple_list_item_1, everything);

        lv_Muistutusdata.setAdapter(MuistiArrayAdapter);

        lv_Muistutusdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            //Lähettää tiedot luettavaksi "CalendarActivity_viewlle."
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Intent intent = new Intent(Calendar_memory_list.this, CalendarActivity_View.class);
                intent.putExtra("POSITIONM",String.valueOf(position));

                startActivity(intent);
                finish();
            }
        });
    }
}