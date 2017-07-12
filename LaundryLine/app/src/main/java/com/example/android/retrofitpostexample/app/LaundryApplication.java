package com.example.android.retrofitpostexample.app;

import android.app.Application;

import com.example.android.retrofitpostexample.DataTransactionObjects.RateCard;

import java.util.ArrayList;

/**
 * Created by Sonal on 9/28/2016.
 */
public class LaundryApplication extends Application {
    private static LaundryApplication mInstance;
    private ArrayList<RateCard> rateCardArrayList= new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public  static LaundryApplication getInstance(){
        if(mInstance ==null){
            mInstance = new LaundryApplication();
        }
        return  mInstance;
    }

    public ArrayList<RateCard> getRateCardArrayList() {
        return rateCardArrayList;
    }

    public void setRateCardArrayList(ArrayList<RateCard> rateCardArrayList) {
        this.rateCardArrayList = rateCardArrayList;
    }
}
