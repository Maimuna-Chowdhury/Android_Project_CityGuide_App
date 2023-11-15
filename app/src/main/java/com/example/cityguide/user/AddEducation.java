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

public class AddEducation extends AppCompatActivity {
    EditText addName,addCity,addArea,addNum,addType,addRanking,addLoc;
    DatabaseReference reff;
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);
        addName = findViewById(R.id.add_e_name);
        addArea = findViewById(R.id.add_e_area);
        addNum = findViewById(R.id.add_e_num);
        addCity = findViewById(R.id.add_e_city);
        addType = findViewById(R.id.add_e_type);
        addRanking=findViewById(R.id.add_e_ranking);
        nextBtn=findViewById(R.id.add_e_btn);
        addLoc=findViewById(R.id.add_e_loc);
        reff= FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-36539-default-rtdb.firebaseio.com/");
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String shop_name=addName.getText().toString();
                final String shop_num=addNum.getText().toString();
                final String shop_area=addArea.getText().toString();
                final String shop_city=addCity.getText().toString();
                final String shop_type=addType.getText().toString();
                final String shop_price=addRanking.getText().toString();
                final String shop_loc=addLoc.getText().toString();
                reff.child("Education").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(shop_num))
                        {
                            Toast.makeText(AddEducation.this, "Already Added", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            reff.child("Education").child(shop_num).child("Name").setValue(shop_name);
                            reff.child("Education").child(shop_num).child("City").setValue(shop_city);
                            reff.child("Education").child(shop_num).child("Area").setValue(shop_area);
                            reff.child("Education").child(shop_num).child("Number").setValue(shop_num);
                            reff.child("Education").child(shop_num).child("Type").setValue(shop_type);
                            reff.child("Education").child(shop_num).child("Ranking").setValue(shop_price);
                            reff.child("Education").child(shop_num).child("Location").setValue(shop_loc);
                            Toast.makeText(AddEducation.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}