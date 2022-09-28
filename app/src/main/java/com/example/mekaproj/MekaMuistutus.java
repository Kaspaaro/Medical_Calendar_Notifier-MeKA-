package com.example.mekaproj;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import android.widget.TextView;
import java.util.Calendar;
/* @Semen Morozov*/
public class MekaMuistutus extends AppCompatActivity {
    private static final String TAG = "MekaMuistutus";

    Button btnDate, btnTime;

    // buttons activation

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meka_muistutus);
        btnDate=(Button) findViewById(R.id.btn_date);
        btnTime=(Button) findViewById(R.id.btn_time);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
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

            }
        }, hour, min, is24HoursView);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }

    //send data to the Calendar activity page
    public void btn_addToCalendar(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }


}
