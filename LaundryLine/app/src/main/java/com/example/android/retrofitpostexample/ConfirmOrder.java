package com.example.android.retrofitpostexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitpostexample.DataTransactionObjects.RateCard;
import com.example.android.retrofitpostexample.SessionManagement.SessionManagement;
import com.example.android.retrofitpostexample.app.LaundryApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ConfirmOrder extends AppCompatActivity {
    //List view to show data
    private ListView listView1;

    private String finaljsonstring;
    //List of type Pojo that will store type Login name and Pwd which is our data model
    private List<RateCard> rateCardListSelected1;

    ArrayList<RateCard> arraylist;
    ArrayList<RateCard> confirmList = new ArrayList<RateCard>();
    Button buttonConfirm;
    TextView estimatedCost;
    String email1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);


        //Inserting Tool Bar

        Toolbar toolbar = (Toolbar) findViewById(R.id.confirm_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Verify Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        listView1 = (ListView) findViewById(R.id.listViewConfirmOrder);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        estimatedCost = (TextView) findViewById(R.id.estimatedCost);
        //Bundle bundle = getIntent().getExtras();
        //arraylist = getIntent().getParcelableArrayListExtra("mylist");
        arraylist = LaundryApplication.getInstance().getRateCardArrayList();

        int estimated = 0;

        for (RateCard rateCard : arraylist) {
            if ((rateCard.getQuantity()) != 0) {
                Log.d("ConfirmItem: ", "Id: " + rateCard.getId() + "Name: " + rateCard.getName() + "Quantity: " + rateCard.getQuantity() + " ");
                Toast.makeText(ConfirmOrder.this, "Id" + rateCard.getId() + "  Quantity " + rateCard.getQuantity(), Toast.LENGTH_LONG).show();
                estimated = estimated + (rateCard.getQuantity() * rateCard.getPrice());
                confirmList.add(rateCard);
            }
        }

        SessionManagement session;

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        email1 = user.get(SessionManagement.KEY_EMAIL);


//converting in json to send
        JSONArray jsonArray = new JSONArray();
        JSONObject obj = null;
        for (RateCard ratecard : confirmList) {
            obj = new JSONObject();
            try {

                /*obj.put("email",email1);*/
                obj.put("id", ratecard.getId());
                obj.put("name", ratecard.getName());
                obj.put("price", ratecard.getPrice());
                obj.put("quantity", ratecard.getQuantity());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(obj);

        }

        JSONObject finalobject = new JSONObject();
        try {
            finalobject.put("email", email1);
            finalobject.put("confirmlist",jsonArray);
        }catch (JSONException e){
            e.printStackTrace();
        }

        finaljsonstring = finalobject.toString();
        Toast.makeText(ConfirmOrder.this, "Estimated: " + estimated, Toast.LENGTH_LONG).show();
        estimatedCost.setText("Estimated: Rs. " + estimated + "");

        Log.d("CONFIRMED LIST :", confirmList + "");
        listView1.setAdapter(new MyRateAdapter1(ConfirmOrder.this));

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ConfirmOrder.this,AcceptAddress.class);
                intent.putExtra("finaljsonstring",finaljsonstring);
                startActivity(intent);
           /*     insertOrder();*/
            }
        });

    }
    class MyRateAdapter1 extends BaseAdapter {

        Context context;

        public MyRateAdapter1(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return confirmList.size();
        }

        @Override
        public Object getItem(int i) {
            return confirmList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_confirm_order_row, viewGroup, false);

            // LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // View row = inflater.inflate(R.layout.single_confirm_order_row, viewGroup, false);

            TextView name = (TextView) row.findViewById(R.id.confirm_order_item);
            TextView price = (TextView) row.findViewById(R.id.confirm_order_price);
            TextView quantity = (TextView) row.findViewById(R.id.confirm_order_quantity);

            RateCard temp = confirmList.get(i);
            name.setText(temp.getName());
            price.setText("Rs. "+temp.getPrice().toString());
            quantity.setText("Items: "+temp.getQuantity());
            return row;
        }
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
