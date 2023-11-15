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

public class CustomSearch extends ArrayAdapter<SearchPlace> {
    private Activity context;
    private List<SearchPlace> searchList;
    //private List<resDhaka>resList1;
    public CustomSearch(Activity context, List<SearchPlace> searchList) {
        super(context, R.layout.sample_search,searchList);
        this.context=context;
        this.searchList=searchList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_search,null,true);
        SearchPlace searchPlace=searchList.get(position);
        TextView t1=view.findViewById(R.id.ne_se);
        TextView t2=view.findViewById(R.id.ne_loc_se);
        TextView t3=view.findViewById(R.id.ne_city_se);
        TextView t4=view.findViewById(R.id.ne_area_se);
        TextView t5=view.findViewById(R.id.ne_type_se);
        TextView t7=view.findViewById(R.id.ne_price_se);
        Button t6=view.findViewById(R.id.ne_num_se);


        t1.setText(searchPlace.getName());
        t2.setText(searchPlace.getLocation());
        t3.setText(searchPlace.getCity());
        t4.setText(searchPlace.getArea());
        t5.setText(searchPlace.getType());
        t6.setText(searchPlace.getNumber());
        t7.setText(searchPlace.getPrice());



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
