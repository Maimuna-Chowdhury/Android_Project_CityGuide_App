package com.example.cityguide.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cityguide.R;

public class AddMissingPlace extends AppCompatActivity {
    Button addHos,addRes,addBank,addShop,addAny,addEdu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_missing_place);
        addRes=findViewById(R.id.res_btn);
        addHos=findViewById(R.id.hos_btn);
        addBank=findViewById(R.id.bank_btn);
        addShop=findViewById(R.id.shop_btn);
        addAny=findViewById(R.id.any_btn);
        addEdu=findViewById(R.id.edu_btn);
        addRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddMissingRestaurants.class));
            }
        });
        addHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddMissingHospitals.class));
            }
        });
        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddShops.class));
            }
        });
        addBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddBanks.class));
            }
        });
        addAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddPlaces.class));
            }
        });
        addEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddEducation.class));
            }
        });
    }
}