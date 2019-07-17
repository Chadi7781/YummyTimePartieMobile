package com.example.rservitawla.Utlis;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yanchummar on 12/31/17.
 */

public  class  RestaurantUtils {

    private boolean open;
    private String openTime;
    private String closeTime;
    public static String CLOSED_NOW = "Fermé maintenant",
            BOOKINGS_CLOSED = "Reservation fermer", OPEN_NOW = "ouvrir maintenant";

    public RestaurantUtils(boolean open, String openTime, String closeTime) {
        this.open = open;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    public  RestaurantUtils() {

    }
    public static String determineOpenStatus(boolean openBoolean, String openingTime, String closingTime) {
        if (openBoolean) {
            int startTime, endTime;
            String openStatus;
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("Hmm", Locale.FRENCH);
            int getCurrentTime=Integer.valueOf(sdf.format(cal.getTime()));
            Log.d("RestaurantUtils","currenttime"+getCurrentTime);

            try {
                SimpleDateFormat hrFormat = new SimpleDateFormat("H:mm", Locale.FRENCH);
                SimpleDateFormat smallHrFormat = new SimpleDateFormat("Hmm", Locale.FRENCH);
                Date dateObj = hrFormat.parse(openingTime);
                Date dateObjEnd = hrFormat.parse(closingTime);
                startTime = Integer.parseInt(smallHrFormat.format(dateObj));

                endTime = Integer.parseInt(smallHrFormat.format(dateObjEnd));
                Log.d("TSAdapter", "startTime and EndTime: " + startTime + " end: " + endTime);
            } catch (ParseException e) {
                //Exception Handling
                return null;
            }
            if (getCurrentTime < startTime || getCurrentTime > endTime || getCurrentTime == endTime) {
                openStatus = CLOSED_NOW;
            } else {
                openStatus = OPEN_NOW;
            }

            return openStatus;
        } else {
            return BOOKINGS_CLOSED;
        }
    }
}