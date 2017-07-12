package com.example.android.retrofitpostexample.SessionManagement;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

import com.example.android.retrofitpostexample.AfterLogin;
import com.example.android.retrofitpostexample.LaunchingPage;
import com.example.android.retrofitpostexample.LoginActivity;

public class SessionManagement {
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    // Mobile (make variable public to access from outside)
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "1";
    public static final String KEY_REFERAL = "referal";
    // Sharedpref file name
    private static final String PREF_NAME = "LaundaryLine";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Constructor
    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String mobile, String email, String name, int id, String referal) {

        Toast.makeText(_context,"Sessn info2:"+mobile+email+id+referal,Toast.LENGTH_LONG).show();


        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_EMAIL, email);
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_REFERAL, referal);
        // commit changes
        editor.commit();
    }

/*    public void createLoginSession(String name, String mobile){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_MOBILE, mobile);
        // commit changes
        editor.commit();


    }

    */

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // user email id
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
        user.put(KEY_REFERAL,pref.getString(KEY_REFERAL,null));

        Log.d("Session information",user.toString());
        Toast.makeText(_context,"SESSION INFORMSTION123"+user.get("KEY_MOBILE"),Toast.LENGTH_LONG).show();

        // return user
        return user;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {

        // Check login status
        if (!this.isLoggedIn()) {

            Toast.makeText(_context, "New User Login", Toast.LENGTH_LONG).show();
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

}