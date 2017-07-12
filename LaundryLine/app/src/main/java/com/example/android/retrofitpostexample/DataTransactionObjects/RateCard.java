package com.example.android.retrofitpostexample.DataTransactionObjects;


import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RateCard implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;

    private int quantity;

    public RateCard(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        quantity = in.readInt();
    }


    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(quantity);
    }
    public static final Parcelable.Creator<RateCard> CREATOR = new Parcelable.Creator<RateCard>()
    {
        public RateCard createFromParcel(Parcel in)
        {
            return new RateCard(in);
        }
        public RateCard[] newArray(int size)
        {
            return new RateCard[size];
        }
    };
}