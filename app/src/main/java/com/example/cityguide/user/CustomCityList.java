package com.example.cityguide.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cityguide.R;

public class CustomCityList extends BaseAdapter {
    Context context;
    String listCity[];
    LayoutInflater inflater;
    public CustomCityList(Context ctx,String[]cities)
    {
        this.context=ctx;
        this.listCity=cities;
        inflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listCity.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.citylist,null,true);
        TextView txtView=(TextView) convertView.findViewById(R.id.cities_text);
        txtView.setText(listCity[position]);
        return convertView;
    }
}
