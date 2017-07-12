package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitpostexample.DataTransactionObjects.UserInformation;
import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity extends AppCompatActivity {

    UserInformation myProfile ;
    SessionManagement session;
    String name1, mobile1,email1;
    TextView name, mobile, email;
    EditText address;
    Button changeAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar profile_tool = (Toolbar)findViewById(R.id.profile_tool);
        setSupportActionBar(profile_tool);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        name = (TextView) findViewById(R.id.myprofile_name_edittext);
        mobile = (TextView) findViewById(R.id.myprofile_mobileno_edittext);
        email = (TextView) findViewById(R.id.myprofile_email_edittext);

        getUserData();
    }






    void getUserData(){

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();


        // name
        name1 = user.get(SessionManagement.KEY_NAME);
        // mobile
        mobile1 = user.get(SessionManagement.KEY_MOBILE);
        email1 = user.get(SessionManagement.KEY_EMAIL);
        //  id= user.get(SessionManagement.KEY_ID);

        Toast.makeText(ProfileActivity.this, "[Profile]" + name1 + email1+mobile1, Toast.LENGTH_LONG).show();

        try
        {
            name.setText(name1);
            mobile.setText(mobile1);
            email.setText(email1);

        }catch (Exception e){

            Toast.makeText(ProfileActivity.this,"Failed",Toast.LENGTH_LONG).show();
        }


    }

/*
    public void loadProfile(View view) {

        name.setText(name1);
        mobile.setText(mobile1);
        email.setText(email1);
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
