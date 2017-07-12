package com.example.android.retrofitpostexample;

import android.app.ProgressDialog;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.retrofitpostexample.DataTransactionObjects.RateCard;
import com.example.android.retrofitpostexample.app.LaundryApplication;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaceOrder extends AppCompatActivity {
    //List view to show data
    private ListView listView;

    //List of type Pojo that will store type Login name and Pwd which is our data model
    private ArrayList<RateCard> rateCardList;
    Button buttonFinalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.place_order_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Place Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        listView = (ListView) findViewById(R.id.rateCard);
        buttonFinalList = (Button)findViewById(R.id.buttonNext);
        //calling the method that will fetch data
        getRateCard();
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
            public void success(final ArrayList<RateCard> rateCards, Response response) {
                //dismiss the loading progress bar
                loading.dismiss();
                // Toast.makeText(RateCardList.this,rateCards.toString(),Toast.LENGTH_LONG).show();
                //storing the data in list
                rateCardList = rateCards;

                //calling a method to show the list
                // showList();
                listView.setAdapter(new MyRateAdapter(PlaceOrder.this));

                buttonFinalList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle=null;
                        for (RateCard rateCard:rateCards) {
                            if ((rateCard.getQuantity()) != 0) {
                              //  Log.d("Item: ", " " + rateCard.getId() + " " + rateCard.getName() + " " + rateCard.getQuantity() + " ");

                                //bundle.putParcelable("data", rateCard);
                            }
                        }
                        //Passing the Data
                        Intent intent = new Intent(PlaceOrder.this,ConfirmOrder.class);
                        //bundle = new Bundle();
                        //intent.putParcelableArrayListExtra("mylist", rateCardList);
                        //intent.putExtras(bundle);
                        LaundryApplication.getInstance().setRateCardArrayList(rateCardList);
                        startActivity(intent);

                    }
                });
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(PlaceOrder.this, "Data not recived from server", Toast.LENGTH_LONG).show();
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
        public View getView(int position, View view, ViewGroup viewGroup) {

            if(view==null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.single_place_order, viewGroup, false);
            }
            //final TextView itemid = (TextView)row.findViewById(R.id.place_order_id);
            TextView name = (TextView) view.findViewById(R.id.place_order_item);
            TextView price = (TextView) view.findViewById(R.id.place_order_price);
            final RateCard temp = rateCardList.get(position);
            //itemid.setText(temp.getId().toString());
            name.setText(temp.getName());
            price.setText("Rs. "+temp.getPrice().toString());
            //code to handle button clicks

            //Hhahaandle TextView and display string from your list
            final TextView quantityText = (TextView)view.findViewById(R.id.place_order_quantity);
           // quantityText.setText(rateCardList.get(position));

            //Handle buttons and add onClickListeners
            Button minusBtn = (Button)view.findViewById(R.id.place_order_minus);
            Button plusBtn = (Button)view.findViewById(R.id.place_order_plus);

            minusBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                    if(temp.getQuantity()>=1)
                    temp.setQuantity(temp.getQuantity()-1);

                    quantityText.setText(temp.getQuantity()+"");
                    //list.remove(position); //or some other task
                    //notifyDataSetChanged();
                }
            });
            plusBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //do something
                    temp.setQuantity(temp.getQuantity()+1);
                    quantityText.setText(temp.getQuantity()+"");
                    //notifyDataSetChanged();
                }
            });

            return view;
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

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
