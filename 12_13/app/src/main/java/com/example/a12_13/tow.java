package com.example.a12_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class tow extends AppCompatActivity {
    Button lyd_ljw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        lyd_ljw = findViewById(R.id.lyd_ljw);

    }
}