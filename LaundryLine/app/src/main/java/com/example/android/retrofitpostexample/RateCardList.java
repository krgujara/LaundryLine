package com.example.android.retrofitpostexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RateCardList extends AppCompatActivity {

    //List view to show data
    private ListView listView;
    private Button buttonNext;

    //List of type Pojo that will store type Login name and Pwd which is our data model
    private List<RateCard> rateCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_card_list);

        //Tool bar
        Toolbar profile_tool = (Toolbar)findViewById(R.id.ratecard_tool);
        setSupportActionBar(profile_tool);
        getSupportActionBar().setTitle("Rate Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (ListView) findViewById(R.id.listViewRateDetails);
        buttonNext = (Button)findViewById(R.id.buttonNext);


        //calling the method that will fetch data
        getRateCard();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RateCardList.this,PlaceOrder.class);
                startActivity(intent);
            }
        });
    }

    public void getRateCard() {
        //while the app fetched data, show progress bar
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait...", false, false);

        //create the rest adapter
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(MainActivity.ROOT_URL).build();

        //creating an object of our api interface
        RegisterAPI api = restAdapter.create(RegisterAPI.class);

        api.getRateCard(new Callback<ArrayList<RateCard>>() {
            @Override
            public void success(ArrayList<RateCard> rateCards, Response response) {
                //dismiss the loading progress bar
               loading.dismiss();
               // Toast.makeText(RateCardList.this,rateCards.toString(),Toast.LENGTH_LONG).show();
                //storing the data in list
                rateCardList = rateCards;

                //calling a method to show the list
               // showList();
                listView.setAdapter(new MyRateAdapter(RateCardList.this));
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(RateCardList.this, "Data not recived from server", Toast.LENGTH_LONG).show();
            }
        });

    }

    class MyRateAdapter extends BaseAdapter {

        Context context;

        public MyRateAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return rateCardList.size();
        }

        @Override
        public Object getItem(int i) {
            return rateCardList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_rate_row, viewGroup, false);

            TextView name = (TextView) row.findViewById(R.id.textName);
            TextView price = (TextView) row.findViewById(R.id.textPrice);

            RateCard temp = rateCardList.get(i);
            name.setText(temp.getName());
            price.setText("Rs. "+temp.getPrice().toString());
            return row;
        }
    }

    public void showList() {
        //string array to store all user names
        //    String[] name = new String[rateCardList.size()];
        //    Integer[] price = new Integer[rateCardList.size()];

        //Traversing through the whole List to get all the names

        //    for (int i = 0; i < rateCardList.size(); i++) {
        //Storing names in the string array
        //        name[i] = rateCardList.get(i).getName();
        //        price[i] = rateCardList.get(i).getPrice();
        //    }

        //creating an arrayadapter for listview
        // ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);

        //setting adapter to listview
        //listView.setAdapter(new MyRateAdapter(RateCardList.this));


    }


//For back button
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

