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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kaspar Tullus
 * @author Semen Morozov
 */
public class MekaMuistutus extends AppCompatActivity {
    private static final String TAG = "MekaMuistutus";
    String timeTonotify;
    Button btnSDate, btnTime;
    EditText medicineNAME;
    private String setStartingdate;
    private String settime;
    private Calendar calendar;
    private String originaldatetext;
    private String originaltimetext;
    // buttons activation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meka_muistutus);

        //Buttons findbyid on activity
        btnSDate = findViewById(R.id.btn_date);
        btnTime= findViewById(R.id.btn_time);
        medicineNAME =  findViewById(R.id.editTextMedicine);
        originaldatetext = btnSDate.getText().toString();
        originaltimetext = btnTime.getText().toString();


        btnSDate.setOnClickListener(new View.OnClickListener() {
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
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int data) {
                String days = Integer.toString(data);
                String months = Integer.toString(month + 1);
                String years = Integer.toString(year);
                setStartingdate = days + "-" + months + "-" + years;
                btnSDate.setText(setStartingdate);
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
                timeTonotify = hour + ":" + min;
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR,hour);
                calendar1.set(Calendar.MINUTE,min);
                updatetimeTEXT(calendar1);
                btnTime.setText(settime);
            }

        }, hour, min, is24HoursView);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }
    public void updatetimeTEXT(Calendar calendar1){
        String timetext = "";
        timetext = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar1.getTime());
        settime = timetext;
    }


    //send data to the Calendar activity page
    public void btn_addToCalendar(View v) {

        String medname = medicineNAME.getText().toString().trim();                               //access the data form the input field
        String date = btnSDate.getText().toString().trim();                                 //access the date form the choose date button
        String time = btnTime.getText().toString().trim();

        ///Muistutus datan kirjaaminen
        MuistutusData muistutusData;

        try{
            muistutusData = new MuistutusData(-1,medicineNAME.getText().toString(),setStartingdate,settime);

            Toast.makeText(MekaMuistutus.this,"Lisätty",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(MekaMuistutus.this,"Muistutuksen tekemine epäonnistui",Toast.LENGTH_SHORT).show();

            muistutusData = new MuistutusData(0,"ERROR","ERROR","ERROR");

        }
        if (medname.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter text", Toast.LENGTH_SHORT).show();   //shows the toast if input field is empty
        } else {
            if (time.equals(originaltimetext) || date.equals(originaldatetext)) {                                               //shows toast if date and time are not selected
                Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();
            } else {
                setAlarm(medicineNAME.getText().toString(),setStartingdate,settime);
                MekaDataBase mekaDataBase = new MekaDataBase(MekaMuistutus.this);
                boolean success = mekaDataBase.addOneMUIS(muistutusData);
                Intent intent = new Intent(MekaMuistutus.this, Calendar_memory_list.class);
                startActivity(intent);


            }
        }


    }
    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);                   //assigining alaram manager object to set alaram

        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        intent.putExtra("event", text);                                                       //sending data to alarm class to create channel and notification
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getApplicationContext(), "Reminder Set", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);                //this intent will be called once the setting alaram is completes
        intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentBack);                                                                  //navigates from adding reminder activity ot mainactivity

    }
    }


