package com.example.android.retrofitpostexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitpostexample.AlertDialog.AlertDialogManager;
import com.example.android.retrofitpostexample.DataTransactionObjects.RateCard;
import com.example.android.retrofitpostexample.DataTransactionObjects.UserInformation;
import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private UserInformation userInfo;

    TextView textview_log_in, textView_forget_password, textView_incorrect_mobileno_password;
    EditText editText_mobile_no, editText_password;
    Button button_log_in;
    TextView button_not_member;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

/*
        textview_log_in = (TextView) findViewById(R.id.textview_log_in);
        textView_forget_password = (TextView) findViewById(R.id.textView_forget_password);
        textView_incorrect_mobileno_password = (TextView) findViewById(R.id.textView_incorrect_mobileno_password);

*/

        Toolbar my_toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Laundry Line");
        getSupportActionBar().setIcon(R.drawable.ic_action_name);

        editText_mobile_no = (EditText) findViewById(R.id.edittext_mobile_no);
        editText_password = (EditText) findViewById(R.id.editText_password);

        button_log_in = (Button) findViewById(R.id.button_log_in);
        button_not_member = (TextView) findViewById(R.id.button_not_member);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

/*        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,AfterLogin.class);
                startActivity(intent);
            }
        });*/
        button_not_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                //startActivity(i);
                if ((editText_mobile_no.getText().toString().trim().length() > 0) && editText_password.getText().toString().trim().length() > 0) {

                    validateAndLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "Please insert both username and pasword", Toast.LENGTH_LONG).show();
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                }
            }
        });
    }

    public void validateAndLogin() {
        //Here we will handle the http request to insert user to mysql db




        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(MainActivity.ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);
        //Defining the method insertuser of our interface
        api.userLogin(
                //Passing the values by getting it from editTexts
                editText_mobile_no.getText().toString(),
                editText_password.getText().toString(),
                //Creating an anonymous callback
                new Callback<UserInformation>() {
                    @Override
                    public void success(UserInformation userInformation, Response response) {
                        userInfo = userInformation;

                        if (userInfo != null) {

                            Toast.makeText(LoginActivity.this,"In Login Activity: "+userInformation.getName()+userInformation.getMobile(),Toast.LENGTH_LONG).show();

                            session.createLoginSession(editText_mobile_no.getText().toString(), userInformation.getEmail(), userInformation.getName(), userInformation.getId(), userInformation.getReferal());
                            //session.createLoginSession("Laundary Line",editText_mobile_no.getText().toString());
                            Intent i = new Intent(LoginActivity.this, NavigationDrawer.class);
                            startActivity(i);
                            // } else if (output.equals(FAILURE_RESULT)) {
                        } else {
                            //If any error occured displaying the error as toast
                            alert.showAlertDialog(LoginActivity.this, "Login failed..", "Mobile Number/Password is incorrect", false);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
