package com.example.cityguide.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Filter;
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

public class DhakaRestaurants extends AppCompatActivity {
     ListView listView;
     DatabaseReference reff;
     //SearchView searchView;
    EditText search;
     private List<resDhaka>resList;
     private List<resDhaka>filterList;
     private CustomDhakaRes adapter;
     SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhaka_restaurants);
        listView=findViewById(R.id.res_listView);
        search=findViewById(R.id.search_res);

       // searchView=findViewById(R.id.searchViewId);
        reff= FirebaseDatabase.getInstance().getReference("Restaurants");
        resList=new ArrayList<>();
        filterList=new ArrayList<>();
        adapter=new CustomDhakaRes(DhakaRestaurants.this,resList);
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
        for(resDhaka restt:resList)
        {
            if((restt.getName().equals(text))||(restt.getCity().equals(text))||(restt.getArea().equals(text))||(restt.getType().equals(text)))
            {
                filterList.add(restt);
            }
        }
        listView.setAdapter(new CustomDhakaRes(DhakaRestaurants.this,filterList));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resList.clear();
                for(DataSnapshot ds1:snapshot.getChildren())
                {
                    resDhaka restt=ds1.getValue(resDhaka.class);
                    resList.add(restt);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
    //filter


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem myActionMenuItem= menu.findItem(R.id.menu_search);
        SearchView searchView=(SearchView) myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText))
                {
                    adapter.filter("");
                    listView.clearTextFilter();

                }
                else
                {
                 adapter.filter(newText);
                }
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_search)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}