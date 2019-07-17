package com.example.rservitawla.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.RetrofitInstance;
import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.dao.IBanner;
import com.example.rservitawla.models.Banner;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {
    private TextView txt_name,txt_adr;
    private TinyDB tinyDB;
    Button btn_tous;
    com.daimajia.slider.library.SliderLayout sld;
    TextSliderView demoSlider;
    Button btn_autour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB=new TinyDB(this);
        btn_autour=findViewById(R.id.autour);
        btn_tous=findViewById(R.id.tous_resto);
       // sld=findViewById(R.id.slider);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);
        txt_name=headerView.findViewById(R.id.txt_name);
      //  txt_adr=headerView.findViewById(R.id.txt_adr);

        String datastringNom = tinyDB.getString("nomUser");
        Toast.makeText(getApplicationContext(),datastringNom,Toast.LENGTH_LONG).show();
        txt_name.setText(datastringNom);

        btn_autour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindRestaurantActivity.class));
            }
        });

        btn_tous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindRestaurantActivity.class));
            }
        });


    }
    public void getBanner() {

        RetrofitInstance.getRetrofitInstance().create(IBanner.class).getBanners().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.body() != null)
                    DisplayImage(response.body());

            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });
    }
    private void DisplayImage(List<Banner> banners) {
            HashMap<String,String> bannerMap=new HashMap<>();
            for(Banner item:banners){
                 bannerMap.put(item.getNom(),item.getImage());
              for(String nom :bannerMap.keySet()){
                   demoSlider = new TextSliderView(this);
                  demoSlider.description(nom)
                          .image(bannerMap.get(nom)).setScaleType(BaseSliderView.ScaleType.Fit);
                  sld.addSlider(demoSlider);

              }
            }
            demoSlider.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    
                }
            });


     }



    @Override
    public void onClick(View view) {

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_res) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_param) {

        } else if (id == R.id.nav_dec) {
            // Handle the camera action
            new TinyDB(this).clear();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
