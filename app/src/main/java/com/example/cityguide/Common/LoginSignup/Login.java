package com.example.cityguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cityguide.Databases.SessionManager;
import com.example.cityguide.Databases.UserHelperClass;
import com.example.cityguide.LocationOwner.RetailerDashboard;
import com.example.cityguide.R;
import com.example.cityguide.user.AllCategories;
import com.example.cityguide.user.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    TextInputLayout username, pass_word;
    Button signup;
    Button progressbar;
    CheckBox rememberMe;
    DatabaseReference reff;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextInputEditText usernameEditText,passwordEditText;
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);
        username = findViewById(R.id.login_username);
        pass_word = findViewById(R.id.login_password);
        progressbar = findViewById(R.id.login_progrssbar);
        rememberMe = findViewById(R.id.remember_me);
        usernameEditText = findViewById(R.id.user_name);
        passwordEditText = findViewById(R.id.pass_word);
        backbtn=findViewById(R.id.login_back_button);
        signup=findViewById(R.id.login_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.super.onBackPressed();
            }
        });
        //check whether username and password is already saved in shared preferences or not
        SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
        if(sessionManager.checkRememberMe())
        {
            HashMap<String,String>rememberMeDetails=sessionManager.getRememberMeDetailsFromSession();
            usernameEditText.setText(rememberMeDetails.get(sessionManager.KEY_SESSIONUSERNAME));
            passwordEditText.setText(rememberMeDetails.get(sessionManager.KEY_SESSIONPASSWORD));
        }

        DatabaseReference reff = FirebaseDatabase.getInstance().getReferenceFromUrl("https://database-36539-default-rtdb.firebaseio.com/");
        /*progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput=username.getEditText().getText().toString();
                String passInput=pass_word.getEditText().getText().toString();
                //Toast.makeText(Login.this, userInput, Toast.LENGTH_SHORT).show();
            reff.child("UserHelperClass").addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     if(snapshot.hasChild(userInput))
                     {
                         //Toast.makeText(Login.this, "found", Toast.LENGTH_SHORT).show();
                         String getPassword=snapshot.child(userInput).child("password").getValue(String.class);
                         if(getPassword.equals(passInput))
                         {
                             Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                         }
                     }
                     else
                     {
                         Toast.makeText(Login.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });
            }
        });*/


        //taimoor

        progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letTheUserLoggedIn(v);
            }
        });

    }

    public void letTheUserLoggedIn(View view) {
        if(!isConnected(this))
        {
            showCustomDialog();

        }
        if (!validateFields()) {
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        //get data
        String _username = username.getEditText().getText().toString().trim();
        final String _password = pass_word.getEditText().getText().toString().trim();
        if(rememberMe.isChecked())
        {
          SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
          sessionManager.createRememberMeSession(_username,_password);

        }
        Query checkUser = FirebaseDatabase.getInstance().getReference("UserHelperClass").orderByChild("username").equalTo(_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);


                    String systemPassword = snapshot.child(_username).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        pass_word.setError(null);
                        pass_word.setErrorEnabled(false);
                        //Get users data from firebase database
                        String _fullname=snapshot.child(_username).child("fullName").getValue(String.class);
                       // String _username=snapshot.child(_username).child("username").getValue(String.class);
                        String _email=snapshot.child(_username).child("email").getValue(String.class);
                        String _phoneNo=snapshot.child(_username).child("phoneNo").getValue(String.class);
                        String _password=snapshot.child(_username).child("password").getValue(String.class);
                        String _dateofBirth=snapshot.child(_username).child("date").getValue(String.class);
                        String _gender=snapshot.child(_username).child("gender").getValue(String.class);
                        //create a session
                        SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SESSION_USERSESSION);
                        sessionManager.createLogInSession(_fullname,_username,_email,_password,_dateofBirth,_gender);
                        /*if(sessionManager.checkLogin())
                        {
                            Toast.makeText(Login.this, "Already Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                        }*/


                            Toast.makeText(Login.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateofBirth+"\n"+_gender+"\n", Toast.LENGTH_SHORT).show();

                            progressbar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), UserDashboard.class));





                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "No such user exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(Login.this, "Database error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
        builder.setMessage("Please connect to the internet to proceed further").setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(),RetailerStartUpScreen.class));
                finish();
            }
        }).show();
    }

    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager= (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifiConnection!=null && wifiConnection.isConnected())||(mobileConnection!=null && mobileConnection.isConnected()) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    private boolean validateFields() {
        String user_name = username.getEditText().getText().toString().trim();
        String passWord = pass_word.getEditText().getText().toString().trim();

        if (user_name.isEmpty()) {
            username.setError("Username Cannot Be empty");
            username.requestFocus();
            return false;
        }
        if (passWord.isEmpty()) {
            pass_word.setError("Password Cannot Be empty");
            pass_word.requestFocus();
            return false;
        }
        return true;
    }
}






                /*progressbar.setVisibility(View.VISIBLE);
                //get data
                String user_name=username.getEditText().getText().toString().trim();
                String pass_word=password.getEditText().getText().toString().trim();
                //Database
                Query checkUser= FirebaseDatabase.getInstance().getReference("UserHelperClass").orderByChild("username").equalTo(user_name);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        username.setError(null);
                        username.setErrorEnabled(false);


                        String systemPassword=snapshot.child(user_name).child("password").getValue(String.class);
                        if(systemPassword.equals(password))
                        {
                            password.setError(null);
                            password.setErrorEnabled(false);


                        }
                        else
                        {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "No such user exists", Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Database error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/


//}







