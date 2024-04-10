package com.example.linyudeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class two extends AppCompatActivity {
    Button lyd_ljw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        lyd_ljw = findViewById(R.id.lyd_ljw);
        lyd_ljw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(two.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}