package com.example.rservitawla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.R;

public class DateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Calendrier");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




        final CalendarView simpleCalendarView = findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                Toast.makeText(getApplicationContext(), "Date is : " + dayOfMonth + " / " + (month + 1) + " / " + year, Toast.LENGTH_LONG).show();
                String full=dayOfMonth+"/"+(month+1)+"/"+year;
                new TinyDB(DateActivity.this).putString("fulldate","full");
                ////  to get it:                  if(new TinyDB(SplashScreen.this).getString("fulldate").contains("full")){

            }
        });
    }
}
