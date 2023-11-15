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

public class AddMissingShop extends AppCompatActivity {
    EditText addName,addLoc,addPhone;
    Button addBtn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_missing_shops);
        addBtn=findViewById(R.id.add_gro);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //addName=findViewById(R.id.add_s_name);
        //addLoc=findViewById(R.id.add_s_loc);
        //addPhone=findViewById(R.id.add_s_num);
        //addBtn=findViewById(R.id.add_s_btn);
       //reff= FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-36539-default-rtdb.firebaseio.com/");
        /*addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String res_name=addName.getText().toString();
                final String res_loc=addLoc.getText().toString();
                final String res_num=addPhone.getText().toString();
                reff.child("Dhaka Shops").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(res_num))
                        {
                            Toast.makeText(AddMissingShop.this, "Already Added", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            reff.child("Dhaka Shops").child(res_num).child("Name").setValue(res_name);
                            reff.child("Dhaka Shops").child(res_num).child("Location").setValue(res_loc);
                            reff.child("Dhaka Shops").child(res_num).child("Phone").setValue(res_num);

                            Toast.makeText(AddMissingShop.this, "Adding Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/
    }
}