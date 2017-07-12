package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;

import java.util.HashMap;

public class LaunchingPage extends AppCompatActivity {
    SessionManagement session;
    String name,mobile, email,referal;
    String id;
    ImageView LL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching_page);

        LL=(ImageView)findViewById(R.id.LL);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * Call this function whenever you want to check user login
                 * This will redirect user to LoginActivity is he is not
                 * logged in
                 * */
                // Session class instance
                session = new SessionManagement(getApplicationContext());
                session.checkLogin();
                // get user data from session
                HashMap<String, String> user = session.getUserDetails();


                // name
                name = user.get(SessionManagement.KEY_NAME);

                // mobile
                mobile = user.get(SessionManagement.KEY_MOBILE);
                email = user.get(SessionManagement.KEY_EMAIL);
                referal = user.get(SessionManagement.KEY_REFERAL);
              //  id= user.get(SessionManagement.KEY_ID);

                Toast.makeText(LaunchingPage.this, "Session information---> " + name + email+mobile+referal+id, Toast.LENGTH_LONG).show();


                navigateToNext();

/*
                final Intent mainIntent = new Intent(LaunchingPage.this, HomeActivity.class);
                LaunchingPage.this.startActivity(mainIntent);
                LaunchingPage.this.finish();
*/
            }
        }, 2000);




      /*  Thread closeActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   Thread.sleep(50000);
                    // Do some stuff
                    // Execute some code after 2 seconds have passed
                    ImageView image = (ImageView) findViewById(R.id.basket_image1);
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
                    image.startAnimation(animation1);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });
        closeActivity.start();
*/


    }
public void navigateToNext() {
        if (session.isLoggedIn()) {
            Toast.makeText(LaunchingPage.this, "Session informationnav next" + name + email, Toast.LENGTH_LONG).show();
            Intent i = new Intent(LaunchingPage.this, NavigationDrawer.class);
            startActivity(i);
            // finish();
        } else {
            Toast.makeText(LaunchingPage.this, "Not logged in", Toast.LENGTH_LONG).show();
            Intent i = new Intent(LaunchingPage.this, LoginActivity.class);
            startActivity(i);
        }
    }
    //changePage is the method called on onClick event on Layout of the launching page
    //The launching page is empty. Has no textView, imageView,Nothing
    public void changePage(View view) {


         if (session.isLoggedIn()) {
            Toast.makeText(LaunchingPage.this, "Session informationChangepg" + name + email, Toast.LENGTH_LONG).show();
            Intent i = new Intent(LaunchingPage.this, NavigationDrawer.class);
            startActivity(i);
            // finizash();
        } else {
            Toast.makeText(LaunchingPage.this, "Not logged in", Toast.LENGTH_LONG).show();
            Intent i = new Intent(LaunchingPage.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
