package com.example.moveinsync;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    public int count=0;
    public SessionManager(Context context)
    {
        sharedPreferences=context.getSharedPreferences("App",0);
        editor=sharedPreferences.edit();
        editor.apply();
    }
    public  void setFlagA(Boolean flag){
        editor.putBoolean("Key_FlagA",flag);
        editor.commit();
    }
    public  boolean getFlagA(){
        return sharedPreferences.getBoolean("Key_FlagA",false);
    }
    public  void setFlagB(Boolean flag){
        editor.putBoolean("Key_FlagB",flag);
        editor.commit();
    }
    public  boolean getFlagB(){
        return sharedPreferences.getBoolean("Key_FlagB",false);
    }
    public  void setFlagC(Boolean flag){
        editor.putBoolean("Key_FlagC",flag);
        editor.commit();
    }
    public  boolean getFlagC(){
        return sharedPreferences.getBoolean("Key_FlagC",false);
    }
    public  void setCurrentTimeA(String cur)
    {
        editor.putString("Key_TimeA",cur);
        editor.commit();
    }
    public  String getCurrentTimeA(){
        return sharedPreferences.getString("Key_TimeA","");
    }
    public  void setCurrentTimeB(String cur)
    {
        editor.putString("Key_TimeB",cur);
        editor.commit();
    }
    public  String getCurrentTimeB(){
        return sharedPreferences.getString("Key_TimeB","");
    } public  void setCurrentTimeC(String cur)
    {
        editor.putString("Key_TimeC",cur);
        editor.commit();
    }
    public  String getCurrentTimeC(){
        return sharedPreferences.getString("Key_TimeC","");
    }
}