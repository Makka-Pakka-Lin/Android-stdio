package com.example.a12_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class thirdMainActivity extends AppCompatActivity {
    TextView third_tv_text;
    Button third_bt_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        third_tv_text=findViewById(R.id.third_tv_text);
        third_bt_exit=findViewById(R.id.third_bt_exit);
        third_tv_text.setText(getIntent().getStringExtra("myback"));
        third_bt_exit.setOnClickListener(view ->{setResult(2,getIntent().putExtra("myreturn","这事第三幕"));
        finish();});
    }
}
