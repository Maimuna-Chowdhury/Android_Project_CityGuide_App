package com.example.cityguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguide.Databases.UserHelperClass;
import com.example.cityguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {
    //variables
    ImageView backBtn;
    Button next,login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;
   // Button next_btn;
   // DatabaseReference reff;
    long maxID=0;
    String fullName,username,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);
        //hooks
        backBtn=findViewById(R.id.signup_back_button1);
        next=findViewById(R.id.signup_next_button);
        login=findViewById(R.id.signup_login_button);
        titleText=findViewById(R.id.signup_title_text);
        radioGroup=findViewById(R.id.radio_group);
        datePicker=findViewById(R.id.age_picker);
        Intent intent=getIntent();
        fullName=intent.getStringExtra("fullName");
       username=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp2ndClass.super.onBackPressed();
            }
        });
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(),Login.class));
          }
      });


    }

    public void call3rdSignupScreen(View view)
    {
        if(!validateGender()|!validateAge())
        {
            return;
        }
        /*UserHelperClass user=new UserHelperClass();
        reff= FirebaseDatabase.getInstance().getReference().child("UserHelperClass");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxID=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        selectedGender=findViewById(radioGroup.getCheckedRadioButtonId());
        String gender=selectedGender.getText().toString();
        int day=datePicker.getDayOfMonth();
        int month=datePicker.getMonth();
        int year=datePicker.getYear();

        String date=day+"/"+month+"/"+year;
        /*user.setGender(gender);
        user.setDate(date);
        reff.child(String.valueOf(maxID+1)).setValue(user);
        Toast.makeText(SignUp2ndClass.this, "new user added", Toast.LENGTH_SHORT).show();*/



        Intent intent=new Intent(getApplicationContext(),SignUp3rdClass.class);

        //Add Transition
        Pair[] pairs=new Pair[4];
        pairs[0]=new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1]=new Pair<View,String>(next,"transition_next_btn");
        pairs[2]=new Pair<View,String>(login,"transition_login_btn");
        pairs[3]=new Pair<View,String>(titleText,"transition_title_text");
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this,pairs);
        intent.putExtra("fullName",fullName);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("gender",gender);
        intent.putExtra("date",date);

        startActivity(intent,options.toBundle());

    }

    private boolean validateGender()
    {
        if(radioGroup.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(this,"Please Select Gender",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean validateAge()
    {
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);
        int userAge=datePicker.getYear();
        int isAgeValid=currentYear-userAge;
        if(isAgeValid<14)
        {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
           return true;
        }
    }
}