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

public class Banks extends AppCompatActivity {
    ListView listView;
    DatabaseReference reff;
    //SearchView searchView;
    EditText search;
    private List<Bank> bankList;
    private List<Bank>filterList;
    private CustomBank adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        listView=findViewById(R.id.bank_listView);
        search=findViewById(R.id.search_bank);

        bankList=new ArrayList<>();
        filterList=new ArrayList<>();
        adapter=new CustomBank(Banks.this,bankList);



        reff= FirebaseDatabase.getInstance().getReference("Banks");

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
        for(Bank bank:bankList)
        {
            if((bank.getName().equals(text))||(bank.getCity().equals(text))||(bank.getArea().equals(text))||(bank.getType().equals(text)))
            {
                filterList.add(bank);
            }
        }
        listView.setAdapter(new CustomBank(Banks.this,filterList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bankList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    Bank bank=ds1.getValue(Bank.class);
                  //  num=ds1.child("Number").getValue(String.class);
                    bankList.add(bank);

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