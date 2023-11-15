package com.example.cityguide.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cityguide.R;

import java.util.List;

public class CustomShop extends ArrayAdapter<Shop> {
    private Activity context;
    private List<Shop> shopList;
    public CustomShop(Activity context,List<Shop>shopList)
    {
        super(context, R.layout.sample_shop,shopList);
        this.context=context;
        this.shopList=shopList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_shop,null,true);
        Shop shop=shopList.get(position);
        TextView t1=view.findViewById(R.id.ne_shop);
        TextView t2=view.findViewById(R.id.ne_city_shop);
        TextView t3=view.findViewById(R.id.ne_area_shop);
        TextView t4=view.findViewById(R.id.ne_loc_shop);
        TextView t5=view.findViewById(R.id.ne_type_shop);
        Button t6=view.findViewById(R.id.ne_num_shop);
        TextView t7=view.findViewById(R.id.ne_price_shop);

    //    RatingBar r1=view.findViewById(R.id.rating_shop);


        t1.setText(shop.getName());
        t2.setText(shop.getCity());
        t3.setText(shop.getArea());
        t4.setText(shop.getLocation());
        t5.setText(shop.getType());
        t6.setText(shop.getNumber());
        t7.setText(shop.getPrice());



        return view;

    }
}
