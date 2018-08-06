package com.aop.myaoptest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import java.util.Random;


public class CheckTokenUtils {
    private static CheckTokenUtils checkToken;
    private  StringBuilder token = new StringBuilder();

    private final SharedPreferences preferences;
    public final static String SP_FILE_NAME = "TokenFile";
    private boolean isFist = true;

    public CheckTokenUtils(Context context) {
        preferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static CheckTokenUtils getInstance(Context context){
        if(checkToken == null){
            synchronized (CheckTokenUtils.class){
                checkToken = new CheckTokenUtils(context);
            }
        }
        return checkToken;
    }

    public SharedPreferences getSp(){
        return this.preferences;
    }

   static Handler handler = new Handler();

    public String getCurrentToken(){
        return this.token.toString();
    }

    public void setToken(String tokenValue){
        token.append(tokenValue);
    }

    public  boolean checkTokenIsOutdate(String currentToken){
        Log.e("tag","currentToken=" + currentToken);
        Log.e("tag","token=" + token.toString());
        if(isFist){
            isFist = false;
            return true;
        } else {
            handler.postDelayed(runnable,8 * 1000);
            if(currentToken.equals(token.toString())){
                return true;
            }
        }
        return false;
    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Random random = new Random();
            int num = random.nextInt(10000);
            token.append("" + num).toString();
            preferences.edit().putString("token",token.toString()).commit();
            handler.postDelayed(runnable,8 * 1000);
        }
    };
}
