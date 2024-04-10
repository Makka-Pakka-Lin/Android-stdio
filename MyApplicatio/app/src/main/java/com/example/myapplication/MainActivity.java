package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_name;

    StringBuilder sb;

    ArrayAdapter maj_adapter;

    TextView tv_name,tv_where,tv_style,tv_text;

    Spinner sp_wan;

    RadioGroup rg_li;

    RadioButton rg_jing,rg_wan;

    String[] major_string={"中国大陆","东南亚国","港澳地区","欧洲国家","北美国家"};

    Button bt_ok,bt_exit,ljw;

    CheckBox jiu,choujiang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();
    }

    private void init() {
        tv_style=findViewById(R.id.tv_style);
        tv_name=findViewById(R.id.tv_name);
        tv_text=findViewById(R.id.tv_text);
        tv_where=findViewById(R.id.tv_where);
        et_name=findViewById(R.id.et_name);
        sp_wan=findViewById(R.id.sp_wan);
        rg_li=findViewById(R.id.rg_li);
        rg_jing=findViewById(R.id.rg_jing);
        rg_wan = findViewById(R.id.rg_wan);
        bt_ok = findViewById(R.id.bt_ok);
        ljw = findViewById(R.id.ljw);
        bt_exit = findViewById(R.id.bt_exit);
        jiu = findViewById(R.id.jiu);
        choujiang = findViewById(R.id.choujiang);
    }//初始化
    private void event(){
        bt_ok.setOnClickListener(View->clear());
        bt_exit.setOnClickListener(View->finish());

    }//事件触发

    private void clear(){
        et_name.setText("");
        rg_jing.setChecked(true);//强制选中“true”
        sp_wan.setSelection(0,true);//选中第0个位置的，初始化
        jiu.setChecked(false);
        choujiang.setChecked(false);
    };
}
