package com.example.linyudeng;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText lyd_et_name;

    StringBuilder sb;

    ArrayAdapter maj_adapter;

    TextView lyd_tv_name,lyd_tv_where,lyd_tv_style,lyd_tv_text;

    Spinner lyd_sp_wan;

    RadioGroup lyd_rg_li;

    RadioButton lyd_rg_jing,lyd_rg_wan;

    String[] major_string={"中国华东区域","中国华南区域","中国西部区域","中国华北区域"};

    Button lyd_bt_ok,lyd_bt_next,lyd_bt_exit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        event();
        registerForContextMenu(lyd_tv_text);//将上下文菜单绑定到tv_display中

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1 && resultCode ==2){
            lyd_tv_text.setText(data.getStringExtra("myreturn"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }//新参：请求，返回，意图

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int num =v.getId();
        if (num == R.id.lyd_tv_text) {
            menu.add(0, Menu.FIRST, 0, "样式一");
            menu.add(0, Menu.FIRST + 1, 0, "样式二");
            menu.add(0, Menu.FIRST + 2, 0, "样式三");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
                lyd_tv_text.setTextAppearance(R.style.myInfo);
                break;
            case Menu.FIRST+1:
                lyd_tv_text.setTextAppearance(R.style.myInfo1);
                break;
            case Menu.FIRST+2:
                lyd_tv_text.setTextAppearance(R.style.myInfo2);
                break;

        }
        return super.onContextItemSelected(item);
    }
    //上下文菜单里的控件被点击后执行的代码
    private void init() {
        lyd_tv_style=findViewById(R.id.lyd_tv_style);
        lyd_tv_name=findViewById(R.id.lyd_tv_name);
        lyd_tv_text=findViewById(R.id.lyd_tv_text);
        lyd_tv_where=findViewById(R.id.lyd_tv_where);
        lyd_et_name=findViewById(R.id.lyd_et_name);
        lyd_sp_wan=findViewById(R.id.lyd_sp_wan);
        lyd_rg_li=findViewById(R.id.lyd_rg_li);
        lyd_rg_jing=findViewById(R.id.lyd_rg_jing);
        lyd_rg_wan = findViewById(R.id.lyd_rg_wan);
        lyd_bt_ok = findViewById(R.id.lyd_bt_ok);
        lyd_bt_next = findViewById(R.id.lyd_bt_next);
        lyd_bt_exit = findViewById(R.id.lyd_bt_exit);
    }//定义控件
    private void event(){
        lyd_bt_ok.setOnClickListener(View->clear());
        lyd_bt_exit.setOnClickListener(View->finish());//代替上面的代码，简约模式   单接口可以用这种lambda，双接口不行
        lyd_bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, two.class);
                startActivity(intent);
            }
        });
        lyd_tv_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){//失去焦点
                    freshInfo();
                }
            }
        });
        lyd_sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freshInfo();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lyd_rg_jing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });

        lyd_sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(major_string[i]));
                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(major_string[i]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lyd_sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(major_string[i]));
                freshInfo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }//事件触发

    private void clear(){

        /**
         * 清除所有输出，初始化
         */
        lyd_et_name.setText("");
        lyd_rg_jing.setChecked(true);//强制选中“true”
        lyd_sp_wan.setSelection(0,true);//选中第0个位置的，初始化
    };
    private void freshInfo(){
        sb = new StringBuilder();
        sb.append("[商品名称]");
        sb.append(lyd_et_name.getText().toString());
        sb.append("[销售区域]");
        sb.append(lyd_rg_jing.isChecked()?lyd_rg_jing.getText().toString():lyd_rg_wan.getText().toString());
        sb.append("[商品属性]");
        sb.append(lyd_sp_wan.getSelectedItem().toString());
    }
}
