package com.example.android.retrofitpostexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrderSummary extends AppCompatActivity {

    Button button;
    String emailandorder;
    String address1;
    String address2;
    private String finaljsonstring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);




        button  =(Button)findViewById(R.id.finalOrderPlaceButton);
        //get Date from confirmOrder Page
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            emailandorder =(String) b.get("confirmOrderWithEmail");
            address1  = (String)b.get("addressline1");
            address2 = (String)b.get("addressline2");

            JSONObject finalobject1 = new JSONObject();
            try {
                finalobject1.put("address1", address1);
                finalobject1.put("address2",address2);
            }catch (JSONException e){
                e.printStackTrace();
            }

            finaljsonstring = finalobject1.toString();

            Log.d("Email and Order",emailandorder);
            Log.d("Order Address1",address1);
            Log.d("Order Address2",address2);
            Toast.makeText(OrderSummary.this,""+emailandorder+address1+address2,Toast.LENGTH_LONG).show();



        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {


        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(MainActivity.ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);
        //Defining the method insertuser of our interface
        api.registerOrder(
                //Passing the values by getting it from editTexts
                emailandorder,
             finaljsonstring,

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();

                            if (output.equals("{result:'fail'}")) {
                                Toast.makeText(OrderSummary.this, "Failed to Place Order", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(OrderSummary.this, "Order Sucessfully Placed", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(OrderSummary.this, NavigationDrawer.class);
                                startActivity(intent);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(OrderSummary.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(OrderSummary.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }


}
