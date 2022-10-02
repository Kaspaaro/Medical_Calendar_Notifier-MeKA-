package com.example.mekaproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 *
 * @author Kaspar Tullus
 */

public class CalendarActivity_View extends AppCompatActivity {
    private TextView date, time;
    private TextView tv_MuistutusSPAIVA;
    private TextView tv_MEDnimi;
    private TextView tv_MTIME;
    private MuistutusData Muget;
    private MekaDataBase mekget;


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
        int id = muistutus.getIdM();

        String medname = muistutus.getMedName();
        String startdate = muistutus.getStartDate();
        String timem = muistutus.getTime();

        tv_MEDnimi.setText(medname);
        tv_MuistutusSPAIVA.setText(startdate);
        tv_MTIME.setText(timem);
        Muget = muistutus;
        mekget = muistdata;
    }
    public void btn_calendar_Delete (View view){
        mekget.deleteOneM(Muget);
        Intent intent = new Intent(CalendarActivity_View.this, Calendar_memory_list.class);
        startActivity(intent);
        finish();
    }





//        @NonNull
//        @Override
//        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//            //return super.onCreateDialog(savedInstanceState);
//            //< start with actual Time >
//            Calendar cal=Calendar.getInstance();
//            int intHour=cal.get(Calendar.HOUR);
//            int intMinute=cal.get(Calendar.MINUTE);
//            //</ start with actual Time >
//
//            return new TimePickerDialog( getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(),intHour,intMinute,true);
//        }







    }

