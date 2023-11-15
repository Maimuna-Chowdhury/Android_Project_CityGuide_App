package com.example.cityguide.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cityguide.R;

public class Citylist extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
       listView=findViewById(R.id.cities_id);
        String[] city_names=getResources().getStringArray(R.array.cities);
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(Citylist.this,R.layout.citylist,city_names);
        CustomCityList adapter=new CustomCityList(getApplicationContext(),city_names);
        listView.setAdapter(adapter);

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=city_names[position];
                if(value.equals("Dhaka"))
                {
                        Intent intent1=new Intent(getApplicationContext(),DhakaRestaurants.class);
                        startActivity(intent1);
                }


            }
        });
    }
}