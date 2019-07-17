package com.example.rservitawla.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;

import com.example.rservitawla.Adapter.AdapterCategorie;
import com.example.rservitawla.R;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);



        RecyclerView recyclerView=  findViewById(R.id.rv_list);
        List<item>mList=new ArrayList<>();
        mList.add(new item(R.drawable.salades,"Salades"));
        mList.add(new item(R.drawable.ma9rouna,"MA9rouna"));
        mList.add(new item(R.drawable.sandiwch,"Sandwich"));
        mList.add(new item(R.drawable.jux,"jus"));
        AdapterCategorie adapter=new AdapterCategorie(this,mList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
