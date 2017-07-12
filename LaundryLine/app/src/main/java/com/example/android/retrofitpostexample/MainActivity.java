package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity{
    //Declaring views
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPasword;
    private EditText editTextMobile;
    private EditText editTextReferal;
    private Button buttonRegister;
    private Button buttonLogin;
    private TextView loginLink;

    private String name,email,password,mobileno;

    //This is our root url   http://192.168.1.36:8084/LaundaryLine238/backend/webService/getFeeds
    public static final String ROOT_URL ="http://192.168.1.33:8081/LaundaryLine238/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views

        editTextName = (EditText) findViewById(R.id.registerName);
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        editTextPasword = (EditText) findViewById(R.id.registerPassword);
        editTextMobile = (EditText) findViewById(R.id.registerMobile);
        //editTextReferal = (EditText) findViewById(R.id.registerReferalCode);

        Toolbar my_toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Laundry Line");
/*
        getSupportActionBar().setIcon(R.drawable.ic_action_name);
*/


        buttonRegister = (Button) findViewById(R.id.btnRegister);
/*
        buttonLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
*/
        loginLink = (TextView)findViewById(R.id.link_login);
        //Adding listener to button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                insertUser();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final String name = editTextName.getText().toString();
            /*  if (!Validator.isValidName(name)) {
                    editTextName.setError("Invalid Name");
                }*/

                final String mobileno = editTextMobile.getText().toString();
 /*               if (!Validator.isValidMobileNo(mobileno)) {
                    editTextMobile.setError("Invalid Mobile Number");
                }*/


                final String email = editTextEmail.getText().toString();
              /*  if (!Validator.isValidEmail(email)) {
                    editTextEmail.setError("Invalid Email");
                }*/

                final String password =  editTextPasword.getText().toString();
                /*if (!Validator.isValidPassword(password)) {
                    editTextEmail.setError("Password must be atleast 6 digits");
                }*/
                 //code to go to login screen
                Toast.makeText(MainActivity.this,"Login page",Toast.LENGTH_LONG).show();

                Intent i  = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });
    }

    public void register(){
        initialize();
        //if (!validate()){
          //  Toast.makeText(this,"Sign up failed",Toast.LENGTH_SHORT).show();

        //}else {
         //   onSignupSuccess();
      //  }


        insertUser();
    }
    public void initialize(){
         name=editTextName.getText().toString().trim();
        email=editTextEmail.getText().toString().trim();
         password=editTextPasword.getText().toString().trim();
         mobileno=editTextMobile.getText().toString().trim();


    }
    public void onSignupSuccess(){

    }

    public boolean validate(){
        boolean valid=true;
        if(name.isEmpty() || name.length()>32){
            editTextName.setError("Please Enter Valid name");
            valid=false;
        }
        if(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter Valid Email Id");
            valid=false;
        }
        if(password.isEmpty() || password.length()<8){
            editTextName.setError("Password must contain atleast 8 digits");
            valid=false;
        }
        if(mobileno.isEmpty() || mobileno.length()>10){
            editTextName.setError("Please Enter Valid Mobile No");
            valid=false;
        }

     return valid;
    }

    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);
        //Defining the method insertuser of our interface
        api.registerUser(

                //Passing the values by getting it from editTexts
                editTextName.getText().toString(),
                editTextEmail.getText().toString(),
                editTextPasword.getText().toString(),
                editTextMobile.getText().toString(),
                /*editTextReferal.getText().toString()*/"nocode",

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try{
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();

                            if(output.equals("{result:'fail'}")){
                                Toast.makeText(MainActivity.this,"User Already Exists",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Login Success!! Welcome",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }


}
