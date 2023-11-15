package com.example.cityguide.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cityguide.R;

public class Restaurants extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4,btn5;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        btn1=findViewById(R.id.contacty);
        btn2=findViewById(R.id.contactk);
        btn2=findViewById(R.id.contactk);
        btn3=findViewById(R.id.contactn);
        btn4=findViewById(R.id.contactp);

        backBtn=findViewById(R.id.back_pressed_res);
        String s1=btn1.getText().toString();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01756785722"));
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:01726-805954"));
                startActivity(intent1);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:01313-361600"));
                startActivity(intent2);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Intent.ACTION_DIAL);
                intent3.setData(Uri.parse("tel:01837-888585"));
                startActivity(intent3);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurants.super.onBackPressed();
            }
        });
    }

}