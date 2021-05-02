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

public class B extends AppCompatActivity {

    Button next2, prev2;
    TextView tv2;
    SessionManager sessionManager;
    private Chronometer chronometer2;
    String currentTime;
    private int counter2;
    SimpleDateFormat format;
    DBHelper2 DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        next2 = findViewById(R.id.next2);
        tv2 = findViewById(R.id.tv2);
        chronometer2 = findViewById(R.id.chronometer2);
        sessionManager=new SessionManager(getApplicationContext());
        format=new SimpleDateFormat("hh:mm:ss:aa");
        currentTime=format.format(new Date());
        tv2=findViewById(R.id.tv2);
        Log.d("bol2",currentTime);
      //  tv2.setText(currentTime);
        Log.d("th2", "hlo2");
        boolean flag=sessionManager.getFlagB();
        Log.d("no", String.valueOf(flag));
        DB= new DBHelper2(this);
        counter2=DB.getCount();
        tv2.setText(String.valueOf(counter2));
        if(!flag) {
            sessionManager.setCurrentTimeB(currentTime);
            sessionManager.setFlagB(true);
            Log.d("set","set");
            chronometer2.start();
        }
        else
        {
            String sessionManagerCurTime=sessionManager.getCurrentTimeB();
            try {
                Date date1=format.parse(sessionManagerCurTime);
                Date date2=format.parse(currentTime);
                Log.d("cur",currentTime);
                long mils=date2.getTime()-date1.getTime();
                chronometer2.setBase(SystemClock.elapsedRealtime()-mils);
                chronometer2.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Log.d("pre", "yes");
        Log.d("cur", currentTime);
        chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chrono) {
                if((SystemClock.elapsedRealtime()-chrono.getBase())>=5000){
                    chrono.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(B.this, "Bing", Toast.LENGTH_SHORT).show();
                    Log.d("ab", "ab");
                    counter2++;
                        Contact2 harry = new Contact2();
                        harry.setCount(counter2);
                        DB.insertData(harry);
                        tv2.setText(String.valueOf(counter2));
                    Log.d("ab2", "ab2");
                }
            }
        });

        next2=findViewById(R.id.next2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer2.setBase(SystemClock.elapsedRealtime());
                sessionManager.setCurrentTimeB(currentTime);
                Toast.makeText(B.this, "Moving to C", Toast.LENGTH_LONG).show();
                Intent inten=new Intent(B.this, C.class);
                int f=DB.getCount();
                Log.d("sam", String.valueOf(f));
                sessionManager.setFlagA(false);
                sessionManager.setFlagB(false);
                sessionManager.setFlagB(false);
                startActivity(inten);
            }
        });
          prev2 = findViewById(R.id.pre2);
            prev2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(B.this, "Moving to A", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(B.this, MainActivity.class);

                    sessionManager.setFlagA(false);
                    sessionManager.setFlagB(false);
                    sessionManager.setFlagC(false);
                    Log.d("this", "this");
                    startActivity(intent);
                }
            });
        }
    }