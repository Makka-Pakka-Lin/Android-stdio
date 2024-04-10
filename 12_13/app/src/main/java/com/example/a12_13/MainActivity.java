package com.example.a12_13;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    EditText et_name,et_address,et_age;
    RadioButton rb_male,rb_female;
    CheckBox cb_basketball,cb_football,cb_tennis;
    Button bt_ok,bt_exit;
    RadioGroup rg_gender;
    Spinner sp_depatrment,sp_major;
    String[][] major_string={{"船舶驾驶","船舶设计","船舶维修"},{"汽车营销","发动机修理","新能源汽车"},{"施工监理","钢结构设计","桥梁设计"},{"物联网应用","通信技术","计算机网络"}};

    ArrayAdapter maj_adapter;

    StringBuilder sb;
    TextView tv_display;

    LinearLayout ll_all;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// 把这个座位layout


        init();//2、初始化对象(与xmL 文件中的控件联系)

        event();//完成所有事件的定义

        registerForContextMenu(tv_display);//将上下文菜单绑定到tv_display中
        registerForContextMenu(et_name);//
        //上下文菜单有多个，也可以ed_name
        //需要在这里注册，声明tv_display是一个上下文菜单
        //在下面函数进行判断是那个菜单，这里声明


    }

    /**
     *
     *下面方法是在用户选择菜单键的情况下被调用
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.mymenu, menu);//把R.menu.mymenu贴到menu上面
        menu.add(0,Menu.FIRST,0,"灰色背景");
        menu.add(0,Menu.FIRST+1,0,"图像背景");
        SubMenu sub = menu.addSubMenu(0,Menu.FIRST+2,0,"薪Acticity");
        sub.add(0,Menu.FIRST+3,0,"非返回跳转");
        sub.add(0,Menu.FIRST+4,0,"返回式跳转");
        //finish();
        return true;
        //return super.onCreateOptionsMenu(menu);
    }//当你点中菜单按钮的时候才会构建

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                ll_all.setBackgroundResource(R.color.lightgrey);
                break;
            case Menu.FIRST+1:
                ll_all.setBackgroundResource(R.mipmap.ac);
                break;
            case Menu.FIRST+3:
                Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
                intent.putExtra("myinfo",sb.toString());
                startActivity(intent);
                //finish();
                break;
            case Menu.FIRST+4:
                Intent intent1=new Intent(MainActivity.this, thirdMainActivity.class);
                intent1.putExtra("myback","这是来自第一屏的返回跳转");
                startActivityForResult(intent1,1);
                break;

                //灰色背景
        }
//        int num = item.getItemId();
//        if (num == R.id.menu_option_gray) ll_all.setBackgroundResource(R.color.lightgrey);
//        else if (num == R.id.menu_option_image) ll_all.setBackgroundResource(R.mipmap.ac);
        return super.onOptionsItemSelected(item);
        //点中菜单键中的某一项后构建
    }
    //点击选择性菜单的一个按键


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1 && resultCode ==2){
            tv_display.setText(data.getStringExtra("myreturn"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }//新参：请求，返回，意图


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int num =v.getId();
        if (num == R.id.tv_display) {
            menu.add(0, Menu.FIRST, 0, "原始样式");
            menu.add(0, Menu.FIRST + 1, 0, "样式一");
            menu.add(0, Menu.FIRST + 2, 0, "样式二");
        }
        if(num == R.id.et_name){
            menu.add(0, Menu.FIRST+3, 0, "原始样式");
            menu.add(0, Menu.FIRST + 4, 0, "样式一");
            menu.add(0, Menu.FIRST + 5, 0, "样式二");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }//长按，也就是上下文菜单，长按出菜单
    //点击被注册的上下文菜单的时候
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
                tv_display.setTextAppearance(R.style.myInfo);
                break;
            case Menu.FIRST+1:
                tv_display.setTextAppearance(R.style.myInfo1);
                break;
            case Menu.FIRST+2:
                tv_display.setTextAppearance(R.style.myInfo2);
                break;

        }
        return super.onContextItemSelected(item);
    }
    //上下文菜单里的控件被点击后执行的代码
    private void init() {
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        et_address=findViewById(R.id.et_address);
        cb_basketball=findViewById(R.id.basketball);
        cb_football=findViewById(R.id.football);
        cb_tennis=findViewById(R.id.tennis);
        rb_male=findViewById(R.id.rb_male);
        rb_female=findViewById(R.id.rb_female);
        bt_ok = findViewById(R.id.bt_ok);
        bt_exit = findViewById(R.id.bt_exit);
        rg_gender = findViewById(R.id.rg_gender);
        sp_depatrment=findViewById(R.id.sp_department);
        sp_major=findViewById(R.id.sp_major);
        tv_display=findViewById(R.id.tv_display);
        ll_all = findViewById(R.id.ll_all);

    }//定义控件
    private void event(){
        bt_ok.setOnClickListener(View->clear());
//        bt_ok.setOnClickListener(new View.OnClickListener() {
//                                     @Override
//                                     public void onClick(View v) {
//                                         freshInfo();
//                                     }
//                                 });


//        bt_exit.setOnClickListener(new View.OnClickListener() {
//                                       //一打开软件就执行，如果按了按钮就执行下面的东东，只是定义了事件，没有执行事件
//                                       @Override
//                                       public void onClick(View v) {
//                                           Toast.makeText(MainActivity.this, "ok啦", Toast.LENGTH_SHORT).show();
//                                           et_name.setText("");
//                                           finish();
//                                       }
//                                   });
//        bt_exit.setOnClickListener(View view ->{finish();});//比较简约
        bt_exit.setOnClickListener(View->finish());//代替上面的代码，简约模式   单接口可以用这种lambda，双接口不行


        sp_depatrment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,major_string[i]);
                sp_major.setAdapter(maj_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){//失去焦点
                    if("".equals(et_name.getText().toString().trim())){
                        Toast.makeText(MainActivity.this,"我想吃饭,姓名不能为空",Toast.LENGTH_LONG).show();
                    }else{
                        freshInfo();
                    }

                }
            }
        });

        et_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){//失去焦点
                    if("".equals(et_age.getText().toString().trim())){
                        Toast.makeText(MainActivity.this,"我想吃饭,年龄不能为空",Toast.LENGTH_LONG).show();
                    }else{
                        freshInfo();
                    }

                }
            }
        });//年龄
        et_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){//失去焦点
                    freshInfo();
                }
            }
        });//地址
        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        cb_basketball.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        cb_football.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        cb_tennis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                freshInfo();
            }
        });
        sp_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freshInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_depatrment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                maj_adapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,major_string[i]);
                sp_major.setAdapter(maj_adapter);
                freshInfo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }//事件触发

    private void freshInfo(){
         sb = new StringBuilder();
        sb.append("[姓名]");
        sb.append(et_name.getText().toString());
        sb.append("[年龄]");
        sb.append(et_age.getText().toString());
        sb.append("[地址]");
        sb.append(et_address.getText().toString());
        sb.append('\n');
        //isCecked判断有没有被选中
        sb.append("[性别]");
        sb.append(rb_male.isChecked()?rb_male.getText().toString():rb_female.getText().toString());
        sb.append("[爱好] ");//爱好后面加空格 防止不选爱好导致【爱好】被删 所以加‘ ’空格防止被删
        if(cb_basketball.isChecked()) sb.append(cb_basketball.getText().toString()).append("、");
        if(cb_football.isChecked()) sb.append(cb_football.getText().toString()).append("、");
        if(cb_tennis.isChecked()) sb.append(cb_football.getText().toString()).append("、");
        sb.deleteCharAt(sb.length()-1);//去掉最后一个字符
        sb.append("\n");
        sb.append("[所在分院]");
        sb.append(sp_depatrment.getSelectedItem().toString());
        sb.append("[专业]");
        sb.append(sp_major.getSelectedItem().toString());

        tv_display.setText(sb.toString());

    }
    private void clear(){

        /**
         * 清除所有输出，初始化
         */
        et_name.setText("");
        et_address.setText("");
        et_age.setText("");
        rb_male.setChecked(true);//强制选中“true”
        cb_basketball.setChecked(false);
        cb_football.setChecked(false);
        cb_tennis.setChecked(false);
        sp_depatrment.setSelection(0,true);//选中第0个位置的，初始化
    };
}
