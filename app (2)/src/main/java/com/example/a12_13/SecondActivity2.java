package com.example.a12_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity2 extends AppCompatActivity {
    TextView sec_tv_info;
    Button sec_bt_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sec_tv_info =findViewById(R.id.sec_tv_info);
        sec_bt_exit =findViewById(R.id.sec_bt_exit);
        sec_bt_exit.setOnClickListener(view ->finish());
    }
}