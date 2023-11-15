package com.example.cityguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.cityguide.Databases.UserHelperClass;
import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp3rdClass extends AppCompatActivity {
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    DatabaseReference reff;
    long maxID=0;
    Button btn,login;
    ImageView backBtn;
    //String fullName,username,email,password,gender,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);



        //hooks
        scrollView=findViewById(R.id.signup_3rd_screen_scroll_view);
        phoneNumber=findViewById(R.id.signup_phone_number);
        btn=findViewById(R.id.signup_next_button);
        login=findViewById(R.id.signup_login_button);
        backBtn=findViewById(R.id.signup_back_button2);
        //String _getUserEnteredPhoneNumber=phoneNumber.getEditText().getText().toString().trim();
        String _getUserEnteredPhoneNumber=PhoneNumberUtils.formatNumber(phoneNumber.getEditText().getText().toString());
        Intent intent=getIntent();
        String fullName=intent.getStringExtra("fullName");
        String username=intent.getStringExtra("username");
        String email=intent.getStringExtra("email");
        String password=intent.getStringExtra("password");
        String gender=intent.getStringExtra("gender");
        String date=intent.getStringExtra("date");
     backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            SignUp3rdClass.super.onBackPressed();
         }
     });
        UserHelperClass user=new UserHelperClass(_getUserEnteredPhoneNumber,fullName,username,email,password,date,gender);
        reff= FirebaseDatabase.getInstance().getReference().child("UserHelperClass");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxID=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNumber())
                {
                    return;
                }

                user.setFullName(fullName);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setGender(gender);
                user.setDate(date);
                user.setPhoneNo(_getUserEnteredPhoneNumber);
                //reff.child(String.valueOf(maxID+1)).setValue(user);
                reff.child(username).setValue(user);
                Toast.makeText(SignUp3rdClass.this, "new user added", Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });



    }

    /*public void callVerifyOTPScreen(View view)
    {



        Intent intent1=new Intent(getApplicationContext(),RegistrationSuccessful.class);
        Pair[] pairs=new Pair[1];
        pairs[0]=new Pair<View,String>(scrollView,"transition_OTP_screen");
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this,pairs);
        startActivity(intent1,options.toBundle());






    }*/

    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        //String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            phoneNumber.setError("Field cannot be empty");
            return false;
        } else if (val.length() > 20) {
            phoneNumber.setError("Username is too large");
            return false;
        }  else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}


