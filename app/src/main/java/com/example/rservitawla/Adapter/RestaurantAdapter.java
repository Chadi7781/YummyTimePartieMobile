package com.example.rservitawla.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.activity.DetailRestaurantActivity;
import com.example.rservitawla.R;
import com.example.rservitawla.models.Restaurant;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import android.widget.RatingBar;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> dataList;
    private Context mContext;
    private Restaurant currentRestaurant;
    public static String CLOSED_NOW = "Fermer maintenant",
            BOOKINGS_CLOSED = "Reservation fermer", OPEN_NOW = "ouvrir maintenant";
    String openStatus;

    public RestaurantAdapter(Context mContext, List<Restaurant> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NotNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_list, parent, false);
        return new RestaurantViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NotNull RestaurantViewHolder holder,  final int position) {
        //current restaurant
        currentRestaurant = dataList.get(position);
        holder.txtRestoTitle.setText(currentRestaurant.getName());
        //holder.txtRestoAdr.setText(currentRestaurant.getAdress());
        holder.txtRestoVille.setText(currentRestaurant.getCity());
        holder.txtRestoLoc.setText(currentRestaurant.getLocation());
        holder.txtOpenStatus.setText(currentRestaurant.getOpeningTime());
    holder.txtRestoCuis.setText(currentRestaurant.getCuisine());
      //  holder.ratingBarResto.setRating(Float.parseFloat(currentRestaurant.getRating()));
//  holder.openTime.setText(currentRestaurant.getOpeningTime());
 // holder.closeTime.setText(currentRestaurant.getClosingTime());
        Glide.with(mContext).load(currentRestaurant.getImage())
                .into(holder.imageView);
        holder.cardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, dataList.get(position).getName(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mContext, DetailRestaurantActivity.class);
                new TinyDB(view.getContext()).putString("url_image",currentRestaurant.getImage());
                new TinyDB(view.getContext()).putString("name_image",currentRestaurant.getName());
                new TinyDB(view.getContext()).putString("location",currentRestaurant.getLocation());
                new TinyDB(view.getContext()).putString("city", currentRestaurant.getCity());
                new TinyDB(view.getContext()).putString("opening_time", currentRestaurant.getOpeningTime());
                new TinyDB(view.getContext()).putString("closing_time", currentRestaurant.getClosingTime());
                new TinyDB(view.getContext()).putString("cuisine", currentRestaurant.getCuisine());
               mContext.startActivity(intent);
            }
        });
        //Open status color change
        int openStatusColor;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfHr=new SimpleDateFormat("HHmm");

        Date date=new Date();
        try {
            Date dateObj = sdf.parse(currentRestaurant.getOpeningTime());
            Date dateObjEnd = sdf.parse(currentRestaurant.getClosingTime());
            int startTime = Integer.parseInt(sdfHr.format(dateObj));
            int endTime = Integer.parseInt(sdfHr.format(dateObjEnd));
            int currentTime=Integer.valueOf(sdfHr.format(date));


            if (currentTime>=startTime && currentTime<endTime) {
                openStatusColor = mContext.getResources().getColor(R.color.brightGreen);
                holder.txtOpenStatus.setTextColor(openStatusColor);
                Toast.makeText(mContext, "Ouvert", Toast.LENGTH_SHORT).show();
                openStatus=OPEN_NOW;
                new TinyDB(holder.itemView.getContext()).putString("open_status",openStatus);
                holder.txtOpenStatus.setText(openStatus);
               // holder.openStatusIcon.setBackgroundColor(openStatusColor);

            }
            else{
                Toast.makeText(mContext, "Fermer", Toast.LENGTH_SHORT).show();
                openStatus=CLOSED_NOW;
                holder.txtOpenStatus.setText(openStatus);
                openStatusColor = mContext.getResources().getColor(R.color.brightRed);
                holder.txtOpenStatus.setTextColor(openStatusColor);
             //   holder.openStatusIcon.setBackgroundColor(openStatusColor);
            }
        } catch (ParseException e) {
            Toast.makeText(mContext, "Exception"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView txtRestoTitle, txtRestoAdr, txtRestoCuis, hours, txtRestoVille,txtRestoLoc,
                txtOpenStatus;
        ImageView imageView, openStatusIcon;
        CardView cardParent;
    //    RatingBar ratingBarResto;
        TextView openTime,closeTime;

        RestaurantViewHolder(View itemView) {
            super(itemView);
           openTime=itemView.findViewById(R.id.open_close_time);
            txtRestoTitle = itemView.findViewById(R.id.name);
            txtRestoLoc = itemView.findViewById(R.id.location);
            imageView = itemView.findViewById(R.id.img);
            txtOpenStatus = itemView.findViewById(R.id.open_status);
            txtRestoCuis = itemView.findViewById(R.id.cuisine);
            txtRestoVille = itemView.findViewById(R.id.city);
            cardParent = itemView.findViewById(R.id.card_view);



        }
    }

    public Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }
}