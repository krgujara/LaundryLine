package com.example.android.retrofitpostexample.DataTransactionObjects;


import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class UserInformation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pasword")
    @Expose
    private String pasword;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("referal")
    @Expose
    private String referal;

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
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The pasword
     */
    public String getPasword() {
        return pasword;
    }

    /**
     * @param pasword The pasword
     */
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    /**
     * @return The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return The referal
     */
    public String getReferal() {
        return referal;
    }

    /**
     * @param referal The referal
     */
    public void setReferal(String referal) {
        this.referal = referal;
    }

}
