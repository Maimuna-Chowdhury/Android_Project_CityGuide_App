package com.example.cityguide.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cityguide.R;

public class ShopType extends AppCompatActivity {
    Button shopType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);
        shopType=findViewById(R.id.gro_btn);
        shopType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Shops.class);
                intent.putExtra("category","Grocery");
                startActivity(intent);
            }
        });

    }
}