package com.example.cityguide.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DhakaHospitals extends AppCompatActivity {
    ListView listView;
    DatabaseReference reff;
    EditText search;
    //SearchView searchView;
    private List<hosDhaka> hosList;
    private List<hosDhaka>filterList;
    private CustomDhakaHos adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhaka_hospitals);
        listView=findViewById(R.id.shop_listView);
        search=findViewById(R.id.search_hos);

        hosList=new ArrayList<>();
        filterList=new ArrayList<>();
        adapter=new CustomDhakaHos(DhakaHospitals.this,hosList);
        reff= FirebaseDatabase.getInstance().getReference("Hospitals");
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
        for(hosDhaka hos:hosList)
        {
            if((hos.getName().equals(text))||(hos.getCity().equals(text))||(hos.getArea().equals(text))||(hos.getType().equals(text)))
            {
                filterList.add(hos);
            }
        }
        listView.setAdapter(new CustomDhakaHos(DhakaHospitals.this,filterList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hosList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    hosDhaka hos=ds1.getValue(hosDhaka.class);
                    hosList.add(hos);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
       /* listView=findViewById(R.id.hos_listView);
        // searchView=findViewById(R.id.searchViewId);
        reff= FirebaseDatabase.getInstance().getReference("Dhaka Hospitals");
        hosList=new ArrayList<>();
        adapter=new CustomDhakaHos(DhakaHospitals.this,hosList);


    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hosList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    hosDhaka hostt=ds1.getValue(hosDhaka.class);
                    hosList.add(hostt);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }*/
}