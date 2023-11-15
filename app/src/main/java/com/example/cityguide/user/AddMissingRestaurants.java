package com.example.cityguide.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMissingRestaurants extends AppCompatActivity {
    EditText addname,addloc,addarea,addcity,addnum,addtype,addPrice;
    Button nextbtn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_missing_restaurants);
        addname=findViewById(R.id.add_r_name);
        addloc=findViewById(R.id.add_r_loc);
       addarea=findViewById(R.id.add_r_area);
      addcity=findViewById(R.id.add_r_city);
      addPrice=findViewById(R.id.add_r_price);
        addnum=findViewById(R.id.add_r_num);
        addtype=findViewById(R.id.add_r_type);
        nextbtn=findViewById(R.id.add_r_btn);
        reff= FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-36539-default-rtdb.firebaseio.com/");
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=addname.getText().toString();
                final String loc=addloc.getText().toString();
                final String area=addarea.getText().toString();
               final String city=addcity.getText().toString();
                final String num=addnum.getText().toString();
                final String type=addtype.getText().toString();
                final String price=addPrice.getText().toString();
                reff.child("Restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(num))
                        {
                            Toast.makeText(AddMissingRestaurants.this, "Already added", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            reff.child("Restaurants").child(num).child("Name").setValue(name);
                            reff.child("Restaurants").child(num).child("Location").setValue(loc);
                            reff.child("Restaurants").child(num).child("Area").setValue(area);
                            reff.child("Restaurants").child(num).child("City").setValue(city);
                            reff.child("Restaurants").child(num).child("Number").setValue(num);
                            reff.child("Restaurants").child(num).child("Type").setValue(type);
                            reff.child("Restaurants").child(num).child("Price").setValue(price);
                            Toast.makeText(AddMissingRestaurants.this, "Adding Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddMissingRestaurants.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}