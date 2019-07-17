package com.example.rservitawla.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rservitawla.R;
import com.example.rservitawla.menu.item;


import java.util.List;

public class AdapterCategorie extends RecyclerView.Adapter<AdapterCategorie.myViewHolder> {
    Context mContext;
    List<item>mData;


    public AdapterCategorie(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View v=layoutInflater.inflate(R.layout.menu_list,viewGroup,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
        holder.backgroundImg.setImageResource(mData.get(i).getBackground());
        holder.tv_titre.setText(mData.get(i).getTitre());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{


        ImageView backgroundImg;
        TextView tv_titre;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImg=itemView.findViewById(R.id.background);
            tv_titre=itemView.findViewById(R.id.desc_categorie);
        }
    }
}
