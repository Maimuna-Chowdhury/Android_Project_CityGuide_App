package com.example.cityguide.user;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cityguide.R;

import java.util.List;

public class CustomEdu extends ArrayAdapter<Edu> {
    private Activity context;
    private List<Edu> eduList;
    public CustomEdu(Activity context,List<Edu>eduList)
    {
        super(context, R.layout.sample_edu,eduList);
        this.context=context;
        this.eduList=eduList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_edu,null,true);
        Edu edu=eduList.get(position);
        TextView t1=view.findViewById(R.id.ne_e);
        TextView t2=view.findViewById(R.id.ne_city_e);
        TextView t3=view.findViewById(R.id.ne_area_e);
        TextView t4=view.findViewById(R.id.ne_loc_e);
        TextView t5=view.findViewById(R.id.ne_type_e);
        Button t6=view.findViewById(R.id.ne_num_e);
        TextView t7=view.findViewById(R.id.ne_ranking_e);

        //    RatingBar r1=view.findViewById(R.id.rating_shop);


        t1.setText(edu.getName());
        t2.setText(edu.getCity());
        t3.setText(edu.getArea());
        t4.setText(edu.getLocation());
        t5.setText(edu.getType());
        t6.setText(edu.getNumber());
        t7.setText(edu.getRanking());



        return view;

    }

}
