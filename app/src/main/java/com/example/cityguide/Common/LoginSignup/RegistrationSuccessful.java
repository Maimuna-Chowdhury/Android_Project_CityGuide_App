package com.example.cityguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cityguide.Databases.UserHelperClass;
import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationSuccessful extends AppCompatActivity {
    String phoneNo,fullName,username,email,password,date,gender;
    DatabaseReference reff;
    long maxID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_successful);
        phoneNo=getIntent().getStringExtra("phoneNo");
        fullName=getIntent().getStringExtra("fullName");
        username=getIntent().getStringExtra("username");
        email=getIntent().getStringExtra("email");
        password=getIntent().getStringExtra("password");
        date=getIntent().getStringExtra("date");
        gender=getIntent().getStringExtra("gender");


    }

    private void storeNewUsersData() {

       /* UserHelperClass user=new UserHelperClass(fullName,username,email,phoneNo,password,date,gender);
        reff= FirebaseDatabase.getInstance().getReference().child("UserHelperClass");
        //String name=text1.getText().toString().trim();
       // Float cgpa=Float.parseFloat(text2.getText().toString().trim());
        user.setFullName(fullName);
        user.setPhoneNo(phoneNo);
        user.setUsername(username);
        user.setEmail(email);
        user.setDate(date);
        user.setGender(gender);
        user.setPassword(password);
        reff.child(String.valueOf(maxID+1)).setValue(user);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    maxID=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
       FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference reference=rootNode.getReference("User");
        //DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        //UserHelperClass addNewUser=new UserHelperClass(fullName,username,email,phoneNo,password,date,gender);
        reference.setValue("First record");


    }

}