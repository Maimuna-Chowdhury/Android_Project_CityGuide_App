package com.example.cityguide.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;

import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Shops extends AppCompatActivity {
    ListView listView;
    DatabaseReference reff;
    Button btn;
    String num;
    //SearchView searchView;
    EditText search;
    private List<Shop> shopList;
    private List<Shop>filterList;
    private CustomShop adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        listView=findViewById(R.id.shop_listView);
        search=findViewById(R.id.search_shop);

        shopList=new ArrayList<>();
        filterList=new ArrayList<>();
        adapter=new CustomShop(Shops.this,shopList);



        reff= FirebaseDatabase.getInstance().getReference("Shops");

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterList.clear();
                if(s.toString().isEmpty())
                {
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    filter(s.toString());
                }

            }
        });
    }

    private void filter(String text) {
        for(Shop shop:shopList)
        {
            if((shop.getName().equals(text))||(shop.getCity().equals(text))||(shop.getArea().equals(text))||(shop.getType().equals(text)))
            {
                filterList.add(shop);
            }
        }
        listView.setAdapter(new CustomShop(Shops.this,filterList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shopList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    Shop shop=ds1.getValue(Shop.class);
                   num=ds1.child("Number").getValue(String.class);
                    shopList.add(shop);

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

}