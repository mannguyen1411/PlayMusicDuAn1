package com.example.duan1.playmusicduan1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InterruptedIOException;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Thread myTh = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent intent = new Intent(StartScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        myTh.start();
    }
}
