package com.example.a12_13;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.TextView;
public class MainActivity extends Activity {
    EditText et_name,et_address,et_age;
    RadioButton rb_male,rb_female;
    CheckBox cb_basketball,cb_football,cb_tennis;
    Button bt_ok,bt_exit;
    RadioGroup rg_gender;
    Spinner sp_depatrment,sp_major;
    String[][] major_string={{"船舶驾驶","船舶设计","船舶维修"},{"汽车营销","发动机修理","新能源汽车"},{"施工监理","钢结构设计","桥梁设计"},{"物联网应用","通信技术","计算机网络"}};

    ArrayAdapter maj_adapter;

    TextView tv_dispaly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// 把这个座位layout


        init();//2、初始化对象(与xmL 文件中的控件联系)

        event();//完成所有事件的定义


    }


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
        tv_dispaly=findViewById(R.id.tv_display);

    }//定义控件
    private void event(){
        bt_exit.setOnClickListener(View->clear());
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
        StringBuilder sb = new StringBuilder();
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

        tv_dispaly.setText(sb.toString());

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
