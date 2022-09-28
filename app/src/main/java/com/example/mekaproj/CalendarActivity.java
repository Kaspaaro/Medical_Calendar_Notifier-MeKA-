package com.example.mekaproj;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    private TextView date, time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);






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



}