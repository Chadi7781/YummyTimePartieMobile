package com.example.rservitawla.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.rservitawla.Adapter.RestaurantAdapter;
import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.RetrofitInstance;
import com.example.rservitawla.dao.IRestaurant;
import com.example.rservitawla.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindRestaurantActivity extends AppCompatActivity {


    private SharedPreferences permissionStatus;

    //----------------
    private RestaurantAdapter dataAdapter;
    private List<Restaurant> restaurants, filteredList;
    private RecyclerView mRecyclerView;
    Toolbar toolbar;
    private ProgressBar mProgressBar;
    private LinearLayout linearLayout;
    private static String currentCity;
    private static ImageView iconOpenStatus;
    static double latitude, longitude;
    private String openingHour, closingHour;
    //---------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Map");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
        initCollapsingToolbar();


        getRestoList();
    }


    private void getRestoList() {


        IRestaurant service = RetrofitInstance.getRetrofitInstance().create(IRestaurant.class);
        Call<List<Restaurant>> call1 = service.getRestaurantDataService();
        call1.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                mProgressBar.setVisibility(View.GONE);

                Log.d("ListResto", "response:" + response.isSuccessful() + "and" + response.body());
                if (response.isSuccessful() && response.body() != null) {
                    runAnimationAgain();
                    restaurants = response.body();
                    Toast.makeText(FindRestaurantActivity.this, "err" + restaurants, Toast.LENGTH_SHORT).show();
                    dataAdapter = new RestaurantAdapter(FindRestaurantActivity.this, restaurants);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setAdapter(dataAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(FindRestaurantActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();


            }

        });
    }

    private void runAnimationAgain() {

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_left_to_right);

        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.scheduleLayoutAnimation();
    }

    private void init() {
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView = findViewById(R.id.recycler_view_resto_list);
        //   iconOpenStatus=findViewById(R.id.icon_open_status);

    }

    //Get Location
//    public void getLocation(){
//        if(!(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED)){
//            String provider=locationManager.getBestProvider(new Criteria(),false);
//            location=locationManager.getLastKnownLocation(provider);
//            Log.i("FindResto","isLocationNullingetLocation:"+location);
//            if (location != null && currentCity == null){
//                geodecodeCity(location.getLatitude(), location.getLongitude());
//            }else {
//                if (location != null && currentCity.matches("Trouver  Location")) {
//                    geodecodeCity(location.getLatitude(), location.getLongitude());
//                }
//            }
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
            case R.id.action_location_found:
                // location found
                LocationFound();
                return true;
            case R.id.action_refresh:
                // refresh
                return true;
            case R.id.action_help:
                // help action
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LocationFound() {
        Intent i = new Intent(FindRestaurantActivity.this, LocalisationTrouverActivity.class);
        startActivity(i);
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}