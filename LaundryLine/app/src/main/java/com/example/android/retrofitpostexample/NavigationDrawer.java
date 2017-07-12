package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button b;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
                Intent intent = new Intent(NavigationDrawer.this,PlaceOrder.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SessionManagement sessionManagement = new SessionManagement(NavigationDrawer.this);
            sessionManagement.logoutUser();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

            Intent intent1 = new Intent(NavigationDrawer.this,HomeActivity.class);
            startActivity(intent1);

            /*HomeFragment homeFragment=new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,homeFragment,homeFragment.getTag()).commit();*/

        } else if (id == R.id.nav_myprofile) {
            Intent intent2 = new Intent(NavigationDrawer.this,ProfileActivity.class);
            startActivity(intent2);

            /*MyprofileFragment myprofileFragment =new MyprofileFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,myprofileFragment,myprofileFragment.getTag()).commit();*/


        } else if (id == R.id.nav_ratecard) {
         /*   RatecardFragment ratecardFragment =new RatecardFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,ratecardFragment,ratecardFragment.getTag()).commit();*/
            Intent intent1 = new Intent(NavigationDrawer.this,RateCardList.class);
            startActivity(intent1);


        } else if (id == R.id.nav_monthlycombo) {
            Intent intent = new Intent(NavigationDrawer.this,MonthlyCombo.class);
            startActivity(intent);

            /*
            MonthlycomboFragment monthlycombomFragment=new MonthlycomboFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,monthlycombomFragment,monthlycombomFragment.getTag()).commit();
*/

        } else if (id == R.id.nav_neworder) {

            Intent intent = new Intent(NavigationDrawer.this,PlaceOrder.class);
            startActivity(intent);

            /*
            NeworderFragment neworderFragment=new NeworderFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,neworderFragment,neworderFragment.getTag()).commit();
*/

        } else if (id == R.id.nav_trackmyorder) {

           /* TrackmyorderFragment trackmyorderFragment=new TrackmyorderFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,trackmyorderFragment,trackmyorderFragment.getTag()).commit();
*/
        }else if (id == R.id.nav_referafriend){
            /*ReferafriendFragment referafriendFragment=new ReferafriendFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,referafriendFragment,referafriendFragment.getTag()).commit();
        */
        }else if (id==R.id.nav_aboutus){
       Intent intent=new Intent(NavigationDrawer.this,aboutus.class);
            startActivity(intent);

            /*     AboutusFragment aboutusFragment=new AboutusFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,aboutusFragment,aboutusFragment.getTag()).commit();
*/
        }else if (id==R.id.nav_contactus){

            Intent intent=new Intent(NavigationDrawer.this,contactus.class);
            startActivity(intent);

            /*ContactusFragment contactusFragment=new ContactusFragment();
            FragmentManager  manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment,contactusFragment,contactusFragment.getTag()).commit();
     */
        }
        else if(id==R.id.nav_log_out){
            SessionManagement sessionManagement=new SessionManagement(NavigationDrawer.this);
            sessionManagement.logoutUser();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

