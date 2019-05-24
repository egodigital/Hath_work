package com.example.easycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private Button bt_confirm,bt_back;
    private TextView tv;
    String content="你好";//想返回的内容
    private TextView tv_temp;
    private TextView tv_himi;
    private TextView tv_seata;
    private TextView tv_seath;
    private TextView tv_mirr;


    private EditText et_temp;
    private EditText et_himi;
    private EditText et_seata;
    private EditText et_seath;
    private EditText et_mirr;


    private Switch sw1;
    private Switch sw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bt_confirm=(Button)findViewById(R.id.button3);
        bt_back=(Button)findViewById(R.id.button2);
        tv_temp=(TextView)findViewById(R.id.textViewtemp);
        tv_himi=(TextView)findViewById(R.id.textViewhumil);
        tv_seata=(TextView)findViewById(R.id.textViewseata);
        tv_seath=(TextView)findViewById(R.id.textViewseath);
        tv_mirr=(TextView)findViewById(R.id.textViewmirr);

        et_temp=(EditText)findViewById(R.id.editTexttemp);
        et_himi=(EditText)findViewById(R.id.editTexthumi);
        et_seata=(EditText)findViewById(R.id.editTextseata);
        et_seath=(EditText)findViewById(R.id.editTextseath);
        et_mirr=(EditText)findViewById(R.id.editTextmirr);

        sw1=(Switch)findViewById(R.id.switch1);
        sw2=(Switch)findViewById(R.id.switch2);


        Intent intent = getIntent();
        String tempData = intent.getStringExtra("we");

        Gson gson = new Gson();
        Vehicle v1=gson.fromJson(tempData,Vehicle.class);

        et_temp.setText(""+v1.temperature);
        et_himi.setText(""+v1.humility);
//        et_power.setText(""+v1.power);
        et_mirr.setText(String.format("%.2f", v1.mirr));
        et_seath.setText(""+v1.seath);
        et_seata.setText(String.format("%.2f", v1.seata));
        sw1.setChecked(v1.air);
        sw2.setChecked(v1.motor);




    }
}
