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

public class C extends AppCompatActivity {

    Button prev;
    TextView tv3;
    SessionManager sessionManager;
    private Chronometer chronometer3;
    String currentTime;
    private  int counter3;
    DBHelper2 DB;
    SimpleDateFormat format;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        prev=findViewById(R.id.prev3);
        tv3=findViewById(R.id.tv3);
        chronometer3 = findViewById(R.id.chronometer3);
        sessionManager=new SessionManager(getApplicationContext());
        format=new SimpleDateFormat("hh:mm:ss:aa");
        currentTime=format.format(new Date());
        tv3=findViewById(R.id.tv3);
        boolean flag=sessionManager.getFlagC();
        Log.d("no", String.valueOf(flag));
        DB= new DBHelper2(this);
        counter3=DB.getCount()+1;
        tv3.setText(String.valueOf(counter3));
        if(!flag) {
            sessionManager.setCurrentTimeC(currentTime);
            sessionManager.setFlagC(true);
            Log.d("set","set");
            chronometer3.start();
        }
        else
        {
            String sessionManagerCurTime=sessionManager.getCurrentTimeC();
            try {
                Date date1=format.parse(sessionManagerCurTime);
                Date date2=format.parse(currentTime);
                Log.d("cur",currentTime);
                long mils=date2.getTime()-date1.getTime();
                chronometer3.setBase(SystemClock.elapsedRealtime()-mils);
                chronometer3.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Log.d("pre", "yes");
        Log.d("cur", currentTime);
        chronometer3.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chrono) {
                if((SystemClock.elapsedRealtime()-chrono.getBase())>=5000){
                    chrono.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(C.this, "Bing", Toast.LENGTH_SHORT).show();
                    Log.d("ab", "ab");
                    counter3++;
                    Contact2 harry=new Contact2();
                    harry.setCount(counter3);
                    DB.insertData(harry);
                    tv3.setText(String.valueOf(counter3));
                    Log.d("ab2", "ab2");
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(C.this, "Moving to C", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(C.this,B.class);
                intent.putExtra("her",tv3.getText().toString());
                //intent.putExtra("vk",currentTime);
                //sessionManager.setFlagA(false);
                sessionManager.setFlagB(false);
                sessionManager.setFlagC(false);
                Log.d("hn", "hn");
                startActivity(intent);
            }
        });
    }

}