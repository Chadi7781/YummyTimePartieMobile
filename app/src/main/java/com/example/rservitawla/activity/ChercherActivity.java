package com.example.rservitawla.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

import com.example.rservitawla.R;

public class ChercherActivity  extends AppCompatActivity {
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher);
        searchView=findViewById(R.id.search_view);


    }


}