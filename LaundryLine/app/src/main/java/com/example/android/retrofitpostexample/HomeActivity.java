package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;

public class HomeActivity extends AppCompatActivity {
Button b;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar my_toolbar = (Toolbar)findViewById(R.id.home_tool_bar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.relhome);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, my_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home);
        navigationView.setNavigationItemSelectedListener(this);
*/


        //Marquee
        Animation animationToLeft = new TranslateAnimation(400, -400, 0, 0);
        animationToLeft.setDuration(12000);
       // animationToLeft.setRepeatMode(Animation.RESTART);
        //animationToLeft.setRepeatCount(Animation.INFINITE);

        Animation animationToRight = new TranslateAnimation(-400,400, 0, 0);
        animationToRight.setDuration(12000);
        //animationToRight.setRepeatMode(Animation.RESTART);
        //animationToRight.setRepeatCount(Animation.INFINITE);

        TextView textViewMarqToLeft = (TextView) findViewById(R.id.textViewMarqToLeft);
        TextView textViewMarqToRight = (TextView) findViewById(R.id.textViewMarqToRight);

        textViewMarqToLeft.setAnimation(animationToLeft);
        textViewMarqToRight.setAnimation(animationToRight);
        String textLeft = "Welcome To";
        String textRight = "Laundry Line";
        textViewMarqToLeft.setText(textLeft);
        textViewMarqToRight.setText(textRight);



        ImageView image = (ImageView) findViewById(R.id.washng);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        image.startAnimation(animation1);

        textView=(TextView)findViewById(R.id.welcome_to_laundry_line);
     //   Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        //textView.startAnimation(animation);

        b=(Button)findViewById(R.id.placeorderhome);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PlaceOrder.class);
                startActivity(intent);
            }
        });

    }

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
