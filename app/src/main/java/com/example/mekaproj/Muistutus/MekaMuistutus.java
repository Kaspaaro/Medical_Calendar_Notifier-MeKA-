package com.example.mekaproj.Muistutus;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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

import com.example.mekaproj.MekaDataBase;
import com.example.mekaproj.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Kaspar Tullus
 * @author Semen Morozov
 */
public class MekaMuistutus extends AppCompatActivity {

    String timeTonotify;
    Button btnSDate, btnTime;
    EditText medicineNAME;

    //GENERATES RANDOM NUMBERS FOR NOTIFICATION ID //SO THE CHANCES TO HIT THE SAME ID ARE 0.00001%
    Random random = new Random();
    private int id = random.nextInt();

    private String setStartingdate;
    private String settime;
    private Calendar calendar;
    private String originaldatetext;
    private String originaltimetext;
    private Integer notifyid = id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meka_muistutus);

        //Buttons findbyid
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

    //Pick Date

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

    //Pick Time

    private void setTime(){

        Calendar calendar = Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int min=calendar.get(Calendar.MINUTE);
        boolean is24HoursView=true;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int min) {

                timeTonotify = hour + ":" + min;
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY,hour);
                calendar1.set(Calendar.MINUTE,min);
                updatetimeTEXT(calendar1);
                btnTime.setText(settime);
            }

        }, hour, min, is24HoursView);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.show();
    }

    public void updatetimeTEXT(Calendar calendar1){
        String timetext;
        timetext = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar1.getTime());
        settime = timetext;
    }


    //send data to the Calendar activity page
    public void btn_addToCalendar(View v) {

        String medname = medicineNAME.getText().toString().trim();         //access the data form the input field
        String date = btnSDate.getText().toString().trim();          //access the date from the choose date button
        String time = btnTime.getText().toString();   //access time from the choose time button

        ///Adding Muistutus Data (Notification Data and Database data)
        MuistutusData muistutusData;
        try{
            muistutusData = new MuistutusData(-1,medname,date,time,notifyid);

            Toast.makeText(MekaMuistutus.this,"Lisätty",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(MekaMuistutus.this,"Muistutuksen tekemine epäonnistui",Toast.LENGTH_SHORT).show();

            muistutusData = new MuistutusData(0,"ERROR","ERROR","ERROR",0);

        }

        // Here we check if all fields are filled out,if not we tell the user "Valitse päivä ja aika" or "Kirjoita lääkkeen nimi"

        if (medname.isEmpty()) {

            // if medicine name field is empty we tell him do fill it.
            Toast.makeText(getApplicationContext(), "Kirjoita lääkeen nimi.", Toast.LENGTH_SHORT).show();   //shows the toast if input Lääkeen nimi is empty

        } else {

            if (time.equals(originaltimetext) || date.equals(originaldatetext)) {         //shows toast if date and time are not selected
                Toast.makeText(getApplicationContext(), "Valitse päivä ja aika.", Toast.LENGTH_SHORT).show();

            } else {
                setAlarm(medicineNAME.getText().toString(),setStartingdate,settime);
                MekaDataBase mekaDataBase = new MekaDataBase(MekaMuistutus.this);
                mekaDataBase.addOneMUIS(muistutusData);
                Intent intent = new Intent(MekaMuistutus.this, Calendar_memory_list.class);
                startActivity(intent);
                finish();

            }
        }


    }

    // Here we set the alarm for AlarmManager that will notify us on the right time.
    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);     //assigining alaram manager object to set alaram

        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);

        //sending data to alarm class to create channel and notification
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);
        intent.putExtra("id",notifyid);

        //Sending infromation trough pending intent into alertreciver.
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), notifyid, intent, PendingIntent.FLAG_ONE_SHOT);

        //Formatting the Date
        String dateandtime = date + " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");

        try {

            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getApplicationContext(), "Muistutus Lisätty", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {

            e.printStackTrace();

        }


    }
    }


