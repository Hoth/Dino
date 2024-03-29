package com.example.lastdino;

import android.app.*;
 import android.content.*;
 
 public class Util {
     public static void setAppPreferences(Activity context, String key, String value) {
         SharedPreferences pref = null;
         pref = context.getSharedPreferences(Constants.LOG_TAG, 0);
         SharedPreferences.Editor prefEditor = pref.edit();
 
         prefEditor.putString(key, value);
         prefEditor.commit();
     } 
 
     public static String getAppPreferences(Activity context, String key) {
         String returnValue = null;
         SharedPreferences pref = null;
 
         pref = context.getSharedPreferences(Constants.LOG_TAG, 0);
         returnValue = pref.getString(key, "");
              return returnValue;
     }
 }