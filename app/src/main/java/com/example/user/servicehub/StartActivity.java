package com.example.user.servicehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        long tStart = System.currentTimeMillis();
        long tEnd = System.currentTimeMillis();
        while(tEnd-tStart<1000)
        {
            tEnd = System.currentTimeMillis();
        }
        Intent i=new Intent(StartActivity.this,MainActivity.class);
        startActivity(i);


    }
}
