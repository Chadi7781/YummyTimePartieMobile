package com.example.rservitawla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rservitawla.R;

public class BookingActivity extends AppCompatActivity {
    Button date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ImageView closeButton = (ImageView) findViewById(R.id.close_booking_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingActivity.super.onBackPressed();
            }
        });

        date = findViewById(R.id.pick_date_button);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,DateActivity.class));
            }
        });


    }
}
