package com.example.cityguide.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Education extends AppCompatActivity {
    ListView listView;
    DatabaseReference reff;
    EditText search;
    private List<Edu> eduList;
    private List<Edu>filterList;
    private CustomEdu adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        listView=findViewById(R.id.e_listView);
        search=findViewById(R.id.search_e);

        eduList=new ArrayList<>();
        filterList=new ArrayList<>();
        adapter=new CustomEdu(Education.this,eduList);



        reff= FirebaseDatabase.getInstance().getReference("Education");

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

        for(Edu edu:eduList)
        {
            if((edu.getName().equals(text))||(edu.getCity().equals(text))||(edu.getArea().equals(text))||(edu.getType().equals(text))||(edu.getRanking().equals(text)))
            {
                filterList.add(edu);
            }

        }
        listView.setAdapter(new CustomEdu(Education.this,filterList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eduList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    Edu edu=ds1.getValue(Edu.class);
                    //num=ds1.child("Number").getValue(String.class);
                    eduList.add(edu);

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