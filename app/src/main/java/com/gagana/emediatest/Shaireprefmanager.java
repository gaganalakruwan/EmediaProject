package com.gagana.emediatest;

import android.content.Context;
import android.content.SharedPreferences;

public class Shaireprefmanager {
    private static Shaireprefmanager mInstance;
    private static Context mctx;

    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USER_NAME="username";
    private static final String KEY_USER_EMAIL="useremail";


    private Shaireprefmanager(Context context)
    {
        mctx=context;
    }


    public static synchronized Shaireprefmanager getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new Shaireprefmanager(context);
        }
        return mInstance;
    }

    public boolean setKeyusertName(String username)
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_NAME, username);
        editor.apply();
        return true;
    }

    public boolean setKeyuserEmail(String email)
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
        return true;
    }

    public String getKeyusertName()
    {
        SharedPreferences shaireprefmanager=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return shaireprefmanager.getString(KEY_USER_NAME,null);
    }

    public String getKeyuserEmail()
    {
        SharedPreferences shaireprefmanager=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return shaireprefmanager.getString(KEY_USER_EMAIL,null);
    }
}
