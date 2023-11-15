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

public class CustomBank extends ArrayAdapter<Bank> {
    private Activity context;
    private List<Bank> bankList;
    public CustomBank(Activity context,List<Bank>bankList)
    {
        super(context, R.layout.sample_bank,bankList);
        this.context=context;
        this.bankList=bankList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_bank,null,true);
          Bank bank=bankList.get(position);
        TextView t1=view.findViewById(R.id.ne_bank);
        TextView t2=view.findViewById(R.id.ne_city_bank);
        TextView t3=view.findViewById(R.id.ne_area_bank);
        TextView t4=view.findViewById(R.id.ne_loc_bank);
        TextView t5=view.findViewById(R.id.ne_type_bank);
        Button t6=view.findViewById(R.id.ne_num_bank);

        //    RatingBar r1=view.findViewById(R.id.rating_shop);


        t1.setText(bank.getName());
        t2.setText(bank.getCity());
        t3.setText(bank.getArea());
        t4.setText(bank.getLocation());
        t5.setText(bank.getType());
        t6.setText(bank.getNumber());



        return view;

    }
}
