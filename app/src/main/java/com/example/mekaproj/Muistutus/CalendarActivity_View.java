package com.example.mekaproj.Muistutus;

import android.annotation.SuppressLint;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mekaproj.MekaDataBase;
import com.example.mekaproj.R;

import java.util.List;


/**
 *
 * @author Kaspar Tullus
 */

public class CalendarActivity_View extends AppCompatActivity  {
    private TextView tv_MuistutusSPAIVA;
    private TextView tv_MEDnimi;
    private TextView tv_MTIME;
    private MuistutusData Muget;
    private MekaDataBase mekget;
    private String timem;
    private String startdate;
    private String medname;
    private Integer notifyid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

            //Id kentät
            tv_MuistutusSPAIVA = findViewById(R.id.startDateTXT);
            tv_MEDnimi = findViewById(R.id.editTextNAMEMED);
            tv_MTIME = findViewById(R.id.timeTxt);

        MekaDataBase muistdata = new MekaDataBase(CalendarActivity_View.this);
        List<MuistutusData> arrayList = muistdata.getMuitsAll();

        Intent intent = getIntent();
        String pos = intent.getStringExtra("POSITIONM");

        int position = Integer.parseInt(pos);
        MuistutusData muistutus = arrayList.get(position);

        medname = muistutus.getMedName();
        startdate = muistutus.getStartDate();
        timem = muistutus.getTime();
        notifyid = muistutus.getNotifyid();

            tv_MEDnimi.setText(medname);
            tv_MuistutusSPAIVA.setText(startdate);
            tv_MTIME.setText(timem);
            Muget = muistutus;
            mekget = muistdata;

    }
    public void btn_calendar_Delete (View view){
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent2 = new Intent(getApplicationContext(), AlertReceiver.class);
        intent2.putExtra("id", notifyid);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), notifyid, intent2, PendingIntent.FLAG_ONE_SHOT);

        am.cancel(pendingIntent);

        mekget.deleteOneM(Muget);
        Intent intent = new Intent(CalendarActivity_View.this, Calendar_memory_list.class);
        startActivity(intent);
        finish();
    }

}

