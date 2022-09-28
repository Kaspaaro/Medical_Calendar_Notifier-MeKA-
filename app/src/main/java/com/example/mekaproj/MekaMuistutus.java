package com.example.mekaproj;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/* @Semen Morozov
* @Kaspar Tullus*/
public class MekaMuistutus extends AppCompatActivity {
    private static final String TAG = "MekaMuistutus";

    Button btnSDate,btnEDate, btnTime;
    EditText medicineNAME;
    private String setEndingdate;
    private String setStartingdate;
    private String settime;
    private String daye;
   private String monthe;
   private String yeare;
    private String days;
    private String months;
    private String years;
    // buttons activation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meka_muistutus);

        //Buttons findbyid on activity
        btnSDate =(Button) findViewById(R.id.btn_date);
        btnEDate =(Button) findViewById(R.id.btn_date2);
        btnTime=(Button) findViewById(R.id.btn_time);
        medicineNAME = (EditText) findViewById(R.id.editTextMedicine);



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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int data) {
                String days = Integer.toString(data);
                String months = Integer.toString(month);
                String years = Integer.toString(data);
                setStartingdate = days + "/" + months + "/" + years;
            }
        },year, month, date);
        datePickerDialog.show();
    }

    private void setDate2() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int data) {
                String daye = Integer.toString(data);
                String monthe = Integer.toString(month);
                String yeare = Integer.toString(data);
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
                settime = hour+":"+ min;

            }
        }, hour, min, is24HoursView);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }

    //send data to the Calendar activity page
    public void btn_addToCalendar(View v) {

        ///Päivä kirjan datan kirjaaminen
        MuistutusData muistutusData;

        try{
            muistutusData = new MuistutusData(-1,medicineNAME.getText().toString(),setStartingdate,setEndingdate,settime);

            Toast.makeText(MekaMuistutus.this,"Lisätty",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(MekaMuistutus.this,"Muistutuksen tekemine epäonnistui",Toast.LENGTH_SHORT).show();

            muistutusData = new MuistutusData(0,"ERROR","ERROR","ERROR","ERROR");

        }
        MekaDataBase mekaDataBase = new MekaDataBase(MekaMuistutus.this);
        boolean success = mekaDataBase.addOneMUIS(muistutusData);
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }


}
