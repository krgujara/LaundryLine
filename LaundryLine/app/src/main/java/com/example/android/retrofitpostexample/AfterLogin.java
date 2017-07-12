package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AfterLogin extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button buttonRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonRate = (Button)findViewById(R.id.AfterLoginViewRate);
        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //code to display ratecard
                Intent intent = new Intent(AfterLogin.this,RateCardList.class);
                startActivity(intent);
            }
        });


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
