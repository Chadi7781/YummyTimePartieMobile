package com.example.rservitawla.Utlis;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Date mDate;

    private static final String DATE_FORMAT = "H:mm dd-MM-yyyy";

    public DateTime(){

        Calendar cal = Calendar.getInstance();

            // Initializing
            year = Integer.parseInt(DateFormat.format("yyyy", cal).toString());
            month = Integer.parseInt(DateFormat.format("MM", cal).toString());
            day = Integer.parseInt(DateFormat.format("dd", cal).toString());
            hour = Integer.parseInt(DateFormat.format("H", cal).toString());
            minute = Integer.parseInt(DateFormat.format("mm", cal).toString());
        }

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = "Current Time : " + mdformat.format(calendar.getTime());
        return strDate;
    }
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public Date getDate() {
        return mDate;
    }
}