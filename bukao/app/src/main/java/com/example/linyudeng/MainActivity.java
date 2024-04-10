package com.example.linyudeng;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
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

    EditText et_name;

    StringBuilder sb;

    ArrayAdapter maj_adapter;

    TextView tv_name,tv_where,tv_style,tv_text;

    Spinner sp_wan;

    RadioGroup rg_li;

    RadioButton rg_jing,rg_wan;

    String[] major_string={"中国大陆","美国","法国","英国"};

    Button bt_ok,bt_exit,ljw;

    CheckBox jiu,choujiang;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        event();
        registerForContextMenu(tv_text);//将上下文菜单绑定到tv_display中

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1 && resultCode ==2){
            tv_text.setText(data.getStringExtra("myreturn"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }//新参：请求，返回，意图

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int num =v.getId();
        if (num == R.id.tv_text) {
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
                tv_text.setTextAppearance(R.style.myInfo);
                break;
            case Menu.FIRST+1:
                tv_text.setTextAppearance(R.style.myInfo1);
                break;
            case Menu.FIRST+2:
                tv_text.setTextAppearance(R.style.myInfo2);
                break;

        }
        return super.onContextItemSelected(item);
    }
    //上下文菜单里的控件被点击后执行的代码
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
    }//定义控件
    private void event(){
        bt_ok.setOnClickListener(View->clear());
        bt_exit.setOnClickListener(View->finish());//代替上面的代码，简约模式   单接口可以用这种lambda，双接口不行
        ljw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog2 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage("真诚是杀死自己的必杀技")
                        .setIcon(R.mipmap.ic_launcher)
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog2.show();
            }
        });



        tv_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){//失去焦点
                        freshInfo();
                }
            }
        });

        sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freshInfo();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rg_jing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });

        jiu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        choujiang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(major_string[i]));
                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(major_string[i]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_wan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        et_name.setText("");
        rg_jing.setChecked(true);//强制选中“true”
        sp_wan.setSelection(0,true);//选中第0个位置的，初始化
        jiu.setChecked(false);
        choujiang.setChecked(false);

    };
    private void freshInfo(){
        sb = new StringBuilder();
        sb.append("【商品名称】");
        sb.append(et_name.getText().toString());
        sb.append("【商品区域】");
        sb.append(rg_jing.isChecked()?rg_jing.getText().toString():rg_wan.getText().toString());
        sb.append("【商品属性】");
        sb.append(sp_wan.getSelectedItem().toString());
        sb.append("【优惠政策】 ");
        if(jiu.isChecked()) sb.append(jiu.getText().toString()).append("、");
        if(choujiang.isChecked()) sb.append(choujiang.getText().toString()).append("、");
        sb.deleteCharAt(sb.length()-1);//去掉最后一个字符
        tv_text.setText(sb.toString());
    }
}
