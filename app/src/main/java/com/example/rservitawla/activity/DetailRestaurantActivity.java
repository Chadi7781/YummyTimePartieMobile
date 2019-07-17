package com.example.rservitawla.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.menu.MenuActivity;

public class DetailRestaurantActivity extends AppCompatActivity {
    ImageView iv;
 //   RatingBar ratingBar;
    Toolbar toolbar;
    TextView time;
    TextView tv,tvOpenNow,tvAdress,tvCity,tvCuisine,tvCLoseNow,text_status;
    CardView cvBookTable;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);
        init();
        Glide.with(this).load(new TinyDB(this).getString("url_image"))
                .into(iv);        tvAdress.setText(new TinyDB(this).getString("location"));
        tvCity.setText(new TinyDB(this).getString("city"));
        tvCuisine.setText(new TinyDB(this).getString("cuisine"));
        if(new TinyDB(this).getString("open_status").contains("ouvrir")){
            tvOpenNow.setTextColor(getResources().getColor(R.color.brightGreen));
        }
        else{
            tvOpenNow.setTextColor(getResources().getColor(R.color.brightRed));


        }
        time.setText(new TinyDB(this).getString("opening_time")+"->"+new TinyDB(this).getString("closing_time"));


        //up navigation
        btn = findViewById(R.id.btn_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRestaurantActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        cvBookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailRestaurantActivity.this, BookingActivity.class));
            }
        });


    }

    public void alertOneButton() {

        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Réservation table ")
                .setMessage("Malheureusement le restaurant n'est pas disponible pour le momement, Merci beaucoup pour votre visite")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();
    }

    public void init(){
        iv=findViewById(R.id.image_details);
        tv=findViewById(R.id.name_description);
        tvAdress=findViewById(R.id.resto_loc);
        tvCity=findViewById(R.id.resto_city);
 //       ratingBar=findViewById(R.id.resto_rating_bar);
        tvOpenNow=findViewById(R.id.text_view_open_now);
        cvBookTable=findViewById(R.id.card_view_book_table);
        tvCuisine=findViewById(R.id.resto_cuisine);
         time=findViewById(R.id.time);
//        toolbar =  findViewById(R.id.toolbardetail);
    }
}
