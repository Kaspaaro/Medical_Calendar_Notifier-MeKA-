package com.example.mekaproj;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author Kaspar Tullus
 * @author Semen Morozov
 */
public class MekaMuistutus extends AppCompatActivity {
    private static final String TAG = "MekaMuistutus";

    Button btnSDate,btnEDate, btnTime;
    EditText medicineNAME;
    private String setEndingdate;
    private String setStartingdate;
    private String settime;
    private Calendar calendar;
    // buttons activation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meka_muistutus);

        //Buttons findbyid on activity
        btnSDate = findViewById(R.id.btn_date);
        btnEDate = findViewById(R.id.btn_date2);
        btnTime= findViewById(R.id.btn_time);
        medicineNAME =  findViewById(R.id.editTextMedicine);



        btnSDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDate();
            }
        });

        btnEDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate2();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

    }

    //pick Date value

    private void setDate() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int data) {
                String days = Integer.toString(data);
                String months = Integer.toString(month);
                String years = Integer.toString(year);
                setStartingdate = days + "/" + months + "/" + years;
            }
        },year, month, date);
        datePickerDialog.show();
    }

    private void setDate2() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int data) {

                String daye = Integer.toString(data);
                String monthe = Integer.toString(month);
                String yeare = Integer.toString(year);
                setEndingdate = daye + "/" + monthe + "/" + yeare;
            }
        },year, month, date);
        datePickerDialog.show();
    }

    //pick time value

    private void setTime(){

        Calendar calendar = Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int min=calendar.get(Calendar.MINUTE);
        boolean is24HoursView=true;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int min) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR,hour);
                calendar1.set(Calendar.MINUTE,min);
                updatetimeTEXT(calendar);

            }

        }, hour, min, is24HoursView);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }
    public void updatetimeTEXT(Calendar calendar){
        String timetext = "";
        timetext += DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
        settime = timetext;
    }


    //send data to the Calendar activity page
    public void btn_addToCalendar(View v) {

        ///Muistutus datan kirjaaminen
        MuistutusData muistutusData;

        try{
            muistutusData = new MuistutusData(-1,medicineNAME.getText().toString(),setStartingdate,settime);

            Toast.makeText(MekaMuistutus.this,"Lisätty",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(MekaMuistutus.this,"Muistutuksen tekemine epäonnistui",Toast.LENGTH_SHORT).show();

            muistutusData = new MuistutusData(0,"ERROR","ERROR","ERROR");

        }
        MekaDataBase mekaDataBase = new MekaDataBase(MekaMuistutus.this);
        boolean success = mekaDataBase.addOneMUIS(muistutusData);
        Intent intent = new Intent(this, Calendar_memory_list.class);
        startActivity(intent);
    }



}
