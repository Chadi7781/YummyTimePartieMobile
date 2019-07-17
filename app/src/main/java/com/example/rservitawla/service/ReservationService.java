package com.example.rservitawla.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.activity.ResultatSystemActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReservationService extends Service{

        public static final String NOTIFICATION_CHANNEL_ID = "10001";
        public NotificationCompat.Builder builder;
        private Context mContext;
        private NotificationManager mNotificationManager;
        private NotificationCompat.Builder mBuilder;
        Timer timer;

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {

            Toast.makeText(this, "Service created", Toast.LENGTH_LONG).show();
        }


        @Override
        public int onStartCommand(final Intent intent, int flags, int startId) {
            Toast.makeText(this, "Service comand", Toast.LENGTH_LONG).show();
            new TinyDB(ReservationService.this).putString("event","0");
            updateDisplay();
            return super.onStartCommand(intent, flags, startId);
        }

        private void updateDisplay() {
            timer= new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                   // getJSON("http://events.prixmimi.com/events/notif_event.php");
                    //Retrofit

                    //N7awl time ely na5ouh mel base wn7awlo l seconde
                }

            },0,1000);//Update text every second
        }

        private void addNotification() {
            timer.cancel();
            builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Concernant la réservation ")
                            .setContentText("");
            Intent notificationIntent = new Intent(this, ResultatSystemActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }

        private void getJSON(final String urlWebService) {

            class GetJSON extends AsyncTask<Void, Void, String> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }


                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    try {
                        loadIntoListView(s);
                    } catch (JSONException e) {
                        Log.i("JSONException",e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        URL url = new URL(urlWebService);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String json;
                        while ((json = bufferedReader.readLine()) != null) {
                            sb.append(json).append("\n");
                        }
                        return sb.toString().trim();
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            GetJSON getJSON = new GetJSON();
            getJSON.execute();
        }

        private void loadIntoListView(String json) throws JSONException {
            JSONArray jsonArray = new JSONArray(json);
            String[] heroes = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                new TinyDB(ReservationService.this).putString("eventid","");
                Log.i("idEvent",""+obj.getString("id"));

                addNotification();

            }

        }

}
