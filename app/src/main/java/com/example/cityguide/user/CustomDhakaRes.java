package com.example.cityguide.user;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cityguide.R;

import java.util.List;

public class CustomDhakaRes extends ArrayAdapter<resDhaka> {

    private Activity context;
    private List<resDhaka>resList;
    //private List<resDhaka>resList1;
    public CustomDhakaRes(Activity context, List<resDhaka> resList) {
        super(context, R.layout.sample_res,resList);
        this.context=context;
        this.resList=resList;
    }


    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_res,null,true);
        resDhaka rests=resList.get(position);
        TextView t1=view.findViewById(R.id.ne_r);
        TextView t2=view.findViewById(R.id.ne_loc_r);
        TextView t3=view.findViewById(R.id.ne_city_r);
        TextView t4=view.findViewById(R.id.ne_area_r);
        TextView t5=view.findViewById(R.id.ne_type_r);
        Button t6=view.findViewById(R.id.ne_num_r);
        //RatingBar t7=view.findViewById(R.id.rating_r);
        TextView t8=view.findViewById(R.id.ne_price_r);
       // ImageView t7=view.findViewById(R.id.nei);



        t1.setText(rests.getName());
        t2.setText(rests.getLocation());
        t3.setText(rests.getCity());
        t4.setText(rests.getArea());
        t5.setText(rests.getType());
        t6.setText(rests.getNumber());
        //t7.setRating(rests.getRating());
        t8.setText(rests.getPrice());

      //  t7.setImageURI(Uri.parse(rests.getImage()));



        return view;
    }
    //filter
   /* public void filter(String charText)
    {
        charText=charText.toLowerCase(Locale.getDefault());
        resList.clear();
        if(charText.length()==0)
        {
            resList.addAll(resList1);
        }
        else
        {
            for(resDhaka restt:resList1)
            {
                if(restt.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    resList.add(restt);
                }
            }
        }
        notifyDataSetChanged();
    }*/
   /* private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }*/
}
