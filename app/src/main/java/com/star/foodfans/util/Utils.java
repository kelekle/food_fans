package com.star.foodfans.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.star.foodfans.Application;
import com.star.foodfans.R;
import com.star.foodfans.repository.AppRepository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import okhttp3.ResponseBody;

public class Utils {

    public static void showLongToast(Context context, String pMsg) {
        Toast.makeText(context, pMsg, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String pMsg) {
        Toast.makeText(context, pMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开Activity
     *
     * @param activity
     * @param cls
     */
    public static void start_Activity(Activity activity, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    public static String DateFormat(Date date){
        String isTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss",
                Locale.getDefault());
        isTime = sdf.format(date);
        return isTime;
    }

    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final boolean putValue(Context context, String key,
                                         String value) {
        value = value == null ? "" : value;
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }

    public static final boolean putBooleanValue(Context context, String key,
                                         Boolean value) {
        value = value == null ? false : value;
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }

    /**
     * 移除SharedPreference
     *
     * @param context
     * @param key
     */
    public static final void RemoveValue(Context context, String key) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(key);
        boolean result = editor.commit();
        if (!result) {
            Log.e("移除Shared", "save " + key + " failed");
        }
    }

    private static final SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("elearn", Context.MODE_PRIVATE);
    }

    public static final String getValue(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    public static final Boolean getBooleanValue(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }


    public static String getJson(Context context, String fileName){
        StringBuffer stringBuffer = new StringBuffer();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }


//    public static String DateFormat(String oldstringDate){
//        String newDate;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
//        try {
//            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
//            newDate = dateFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            newDate = oldstringDate;
//        }
//
//        return newDate;
//    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String getLanguage(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getLanguage());
        return country.toLowerCase();
    }

    /**
     * 保存下载的图片流写入SD卡文件
     * @param path  xxx.jpg
     * @param body  image stream
     */
    public static void writeResponseBodyToDisk(String path, ResponseBody body) {
        if(body==null){
            return;
        }
        try {
            InputStream is = body.byteStream();
            File file = new File(path.substring(0, path.lastIndexOf('/')), path.substring(path.lastIndexOf('/') + 1));
            File fileDir = new File(path.substring(0, path.lastIndexOf('/')));
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //If it isn't mounted - we can't write into it.
                return;
            }
            //创建文件
            if (!file.getParentFile().exists()) {
                if(file.getParentFile().mkdirs()){
                }
//                System.out.println(fileDir.getAbsolutePath());
//                System.out.println(path.substring(0, path.lastIndexOf('/')-1));
                try {
                    if(!file.exists())
                        if(file.createNewFile()){
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}