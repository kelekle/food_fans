package com.star.foodfans;

import android.content.Context;

public class Application extends android.app.Application {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getContext(){

        return mContext;
    }

}
