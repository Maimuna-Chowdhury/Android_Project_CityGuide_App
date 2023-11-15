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

public class AddMissingHospitals extends AppCompatActivity {
    EditText addName,addLoc,addPhone,addCity,addArea,addType;
    Button addBtn;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_missing_hospitals);
        addName=findViewById(R.id.add_hos_name);
        addLoc=findViewById(R.id.add_hos_loc);
        addPhone=findViewById(R.id.add_hos_num);
        addCity=findViewById(R.id.add_hos_city);
        addArea=findViewById(R.id.add_hos_area);
        addType=findViewById(R.id.add_hos_type);
        addBtn=findViewById(R.id.add_hos_btn);
        reff= FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-36539-default-rtdb.firebaseio.com/");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String res_name=addName.getText().toString();
                final String res_loc=addLoc.getText().toString();
                final String res_num=addPhone.getText().toString();
                final String res_city=addCity.getText().toString();
                final String res_area=addArea.getText().toString();
                final String res_type=addType.getText().toString();
                reff.child("Hospitals").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(res_num))
                        {
                            Toast.makeText(AddMissingHospitals.this, "Already Added", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            reff.child("Hospitals").child(res_num).child("Name").setValue(res_name);
                            reff.child("Hospitals").child(res_num).child("City").setValue(res_city);
                            reff.child("Hospitals").child(res_num).child("Area").setValue(res_area);
                            reff.child("Hospitals").child(res_num).child("Location").setValue(res_loc);
                            reff.child("Hospitals").child(res_num).child("Number").setValue(res_num);
                            reff.child("Hospitals").child(res_num).child("Type").setValue(res_type);

                            Toast.makeText(AddMissingHospitals.this, "Adding Successful", Toast.LENGTH_SHORT).show();
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