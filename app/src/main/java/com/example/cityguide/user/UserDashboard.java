package com.example.cityguide.user;

import static com.google.android.material.navigation.NavigationView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cityguide.Common.LoginSignup.Login;
import com.example.cityguide.Common.LoginSignup.RetailerStartUpScreen;
//import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesAdapter;
//import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguide.Databases.SessionManager;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.cityguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE=0.7f;
    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    RecyclerView mostViewedRecycler;
   RecyclerView categoriesRecycler;
    LinearLayout contentView;
    Button goBtn;
   private GradientDrawable gradient2,gradient1,gradient3,gradient4;
   ImageView menuIcon;
   //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard2);

        goBtn=findViewById(R.id.go);
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);

        //menu hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        contentView=findViewById(R.id.content);
        goBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
            }
        });

        navigationDrawer();


        featuredRecycler();
        mostViewedRecycler();
       // categoriesRecycler();

    }
    //navigation drawer functions
    private void navigationDrawer() {
        //Navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
       // drawerLayout.setScrimColor(getResources().getColor(android.R.color.white));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
    //normal functions
    public void callRetailerScreens(View view)
    {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_all_categories:
                Intent intent=new Intent(getApplicationContext(),AllCategories.class);
                startActivity(intent);
                break;
            case R.id.nav_restaurants:
                SessionManager sessionManager=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager.checkLogin())
                {
                    startActivity(new Intent(getApplicationContext(),DhakaRestaurants.class));
                }
                else
                {
                    Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            case R.id.nav_logout:
                SessionManager sessionManager1=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                sessionManager1.logoutUserFromSession();
                Toast.makeText(this, "Succesfully logged out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_shop:
                SessionManager sessionManager2=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager2.checkLogin())
                {
                    startActivity(new Intent(getApplicationContext(),Shops.class));
                }
                else
                {
                    Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            case R.id.nav_bank:
                SessionManager sessionManager3=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager3.checkLogin())
                {
                    startActivity(new Intent(getApplicationContext(),Banks.class));
                }
                else
                {
                    Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            case R.id.nav_search:
                SessionManager sessionManager4=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager4.checkLogin())
                {
                    startActivity(new Intent(getApplicationContext(),Search.class));
                }
                else
                {
                    Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            case R.id.nav_login:
                SessionManager sessionManager6=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager6.checkLogin())
                {
                    Toast.makeText(this, "Already Logged in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;

                //startActivity(new Intent(getApplicationContext(),Login.class));
               // break;

            case R.id.nav_education:
                SessionManager sessionManager5=new SessionManager(UserDashboard.this,SessionManager.SESSION_USERSESSION);
                if(sessionManager5.checkLogin())
                {
                    startActivity(new Intent(getApplicationContext(),Education.class));
                }
                else
                {
                    Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                break;
            case R.id.nav_makecall:
            {
                startActivity(new Intent(getApplicationContext(),Restaurants.class));
                break;
            }










            case R.id.nav_add_missing_place:
                startActivity(new Intent(getApplicationContext(),AddMissingPlace.class));
                break;
            case R.id.nav_hospital:
                startActivity(new Intent(getApplicationContext(),DhakaHospitals.class));
                break;



        }
        return true;
    }







    //Recycler views functions
    /*private  void categoriesRecycler() {
        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        most.add(new CategoriesHelperClass()(R.drawable.city_inn, "City inn"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.wendeys, "Wendeys"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.safe_n_save, "Safe n Save"));

       // categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.school_image, "Education"));
        //categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, R.drawable.hospital_image, "HOSPITAL"));
        //categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.restaurant_image, "Restaurant"));
       // categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.shopping_image, "Shopping"));
       // categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.transport_image, "Transport"));
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        //categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }*/
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.city_inn, "City inn","Restuarant & Hotel"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.wendeys, "Wendeys","Fast Food"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.safe_n_save, "Safe n Save","Groceries"));
       // mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img, "Walmart"));
        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass>featuredLocations=new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_inn,"City inn","Most modern facilitated international standard hotel"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.wendeys,"Wendeys","Exclusive patter, meal,pasta, sandwich,nachos"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.safe_n_save,"Safe n Save","Largest Super Market in Khulna Division"));
        adapter=new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }


}
