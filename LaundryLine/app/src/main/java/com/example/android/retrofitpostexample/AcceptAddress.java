package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.retrofitpostexample.AlertDialog.AlertDialogManager;

public class AcceptAddress extends AppCompatActivity {

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    String emailandorder;

    EditText address1,address2;
    Button buttonnextpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_address);

        //Tool Bar
        Toolbar profile_tool = (Toolbar)findViewById(R.id.address_accept_toolbar);
        setSupportActionBar(profile_tool);
        getSupportActionBar().setTitle("Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        address1 = (EditText)findViewById(R.id.etaddress1);
        address2 = (EditText)findViewById(R.id.etaddress2);
        buttonnextpage = (Button) findViewById(R.id.continuetopicktime);

        buttonnextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((address1.getText().toString().trim().length() > 0) && address2.getText().toString().trim().length() > 0) {


                    String addressline1 = address1.getText().toString();
                    String addressline2 = address2.getText().toString();

                    //get Date from confirmOrder Page
                    Intent iin= getIntent();
                    Bundle b = iin.getExtras();

                    if(b!=null)
                    {
                        emailandorder =(String) b.get("finaljsonstring");
                        Log.d("From ConfirmOrder",emailandorder);
                    }

                    Intent intent = new Intent(AcceptAddress.this,OrderSummary.class);
                    intent.putExtra("addressline1",addressline1);
                    intent.putExtra("addressline2",addressline2);
                    intent.putExtra("confirmOrderWithEmail",emailandorder);

                    //Log.d("Accept Address Line 1",addressline1);
                    //Log.d("Accept AddressLine 2",addressline2);
                    startActivity(intent);

                } else {
                    //Toast.makeText(AcceptAddress.this, "Please insert both address and landmark address", Toast.LENGTH_LONG).show();
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    alert.showAlertDialog(AcceptAddress.this, "Enter Complete Address..", "Please insert both Address line1 and Landmark", false);
                }

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
