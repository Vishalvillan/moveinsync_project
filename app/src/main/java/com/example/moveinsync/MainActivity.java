package com.example.moveinsync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private  long pauseoffset;
    Button next;
    private  int counter;
    B b;
    SessionManager sessionManager;
    SimpleDateFormat format;
    String currentTime;
    TextView tv;
    DBHelper2 DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        sessionManager = new SessionManager(getApplicationContext());
        format = new SimpleDateFormat("hh:mm:ss:aa");
        currentTime = format.format(new Date());
        sessionManager.setCurrentTimeB(currentTime);
        tv = findViewById(R.id.tv);
        Log.d("bol", currentTime);
       // tv.setText(currentTime);
        Log.d("th", "hlo");
        boolean flag = sessionManager.getFlagA();
        Log.d("no", String.valueOf(flag));
        DB= new DBHelper2(this);
        counter=DB.getCount();
        tv.setText(String.valueOf(counter));
        if (!flag) {
            sessionManager.setCurrentTimeA(currentTime);
            sessionManager.setFlagA(true);
            chronometer.start();
        } else {
            String sessionManagerCurTime = sessionManager.getCurrentTimeA();
            try {
                Date date1 = format.parse(sessionManagerCurTime);
                Date date2 = format.parse(currentTime);
                Log.d("cur", currentTime);
                long mils = date2.getTime() - date1.getTime();
                chronometer.setBase(SystemClock.elapsedRealtime() - mils);
                chronometer.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Log.d("pre", "yes");
        Log.d("cur", currentTime);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chrono) {
                Log.d("elapsedRealtime()", String.valueOf(SystemClock.elapsedRealtime()));
                Log.d("getBase()", String.valueOf(chrono.getBase()));
                if ((SystemClock.elapsedRealtime() - chrono.getBase()) >= 5000) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Bing", Toast.LENGTH_SHORT).show();
                    Log.d("ab", "ab");
                    counter++;
                    Contact2 harry=new Contact2();
                    harry.setCount(counter);
                    DB.insertData(harry);
                    tv.setText(String.valueOf(counter));
                    Log.d("ab2", "ab2");
                }
            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                sessionManager.setCurrentTimeA(currentTime);
                Toast.makeText(MainActivity.this, "Moving to B", Toast.LENGTH_LONG).show();
                Intent inten = new Intent(MainActivity.this, B.class);
               int f=DB.getCount();
               Log.d("sam", String.valueOf(f));
                sessionManager.setFlagA(false);
                sessionManager.setFlagB(false);
                sessionManager.setFlagB(false);
               startActivity(inten);
            }
        });
    }

}