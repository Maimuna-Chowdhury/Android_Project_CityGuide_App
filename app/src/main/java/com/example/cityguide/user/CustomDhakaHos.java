package com.example.cityguide.user;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cityguide.R;

import java.util.List;

public class CustomDhakaHos extends ArrayAdapter<hosDhaka> {
    private Activity context;
    private List<hosDhaka> hosList;

 public CustomDhakaHos(Activity context,List<hosDhaka>hosList)
 {
     super(context, R.layout.hospitals,hosList);
     this.context=context;
     this.hosList=hosList;
 }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.hospitals,null,true);
        hosDhaka hosts=hosList.get(position);
        TextView t1=view.findViewById(R.id.ne_hos);
        TextView t2=view.findViewById(R.id.ne_city_hos);
        TextView t3=view.findViewById(R.id.ne_area_hos);
        TextView t4=view.findViewById(R.id.ne_loc_hos);
        TextView t5=view.findViewById(R.id.ne_type_hos);
        Button t6=view.findViewById(R.id.ne_num_hos);
        t1.setText(hosts.getName());
        t2.setText(hosts.getCity());
        t3.setText(hosts.getArea());
        t4.setText(hosts.getLocation());
        t5.setText(hosts.getType());
        t6.setText(hosts.getNumber());

        return view;
    }
}
