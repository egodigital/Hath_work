package com.example.easycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {
    private TextView tv1;//item.xml里的TextView：Textviewname
    private TextView tv2;//item.xml里的TextView：Textviewage
    private Button bt,bt_map;//activity_main.xml里的Button
    private ListView lv;//activity_main.xml里的ListView
    private BaseAdapter adapter;//要实现的类
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();//实体类
    private EditText et1;
    private String liststr=null;
    private List<Vehicle> list=null;


    public void get(String url) {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        liststr=responseStr;
//                        et1.setText(responseStr);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.button);
        lv = (ListView) findViewById(R.id.listview1);
        et1=(EditText)findViewById(R.id.editText);
        bt_map=(Button)findViewById(R.id.button_map);

        get("https://raw.githubusercontent.com/max65536/Hath_demo/master/Jsonlist");
        Log.i("info","jsonstr=="+liststr);

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return vehicleList.size();//数目
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view;

                if (convertView==null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.item, null);
                }else{
                    view=convertView;
                    Log.i("info","有缓存，不需要重新生成"+position);
                }
                tv1 = (TextView) view.findViewById(R.id.Textviewdis);//找到Textviewname
                tv1.setText(vehicleList.get(position).getDistance()+"km");//设置参数

                tv2 = (TextView) view.findViewById(R.id.Textviewbat);//找到Textviewage
                tv2.setText(vehicleList.get(position).getLocation()+"");//设置参数
                return view;
            }
            @Override
            public long getItemId(int position) {//取在列表中与指定索引对应的行id
                return 0;
            }
            @Override
            public Object getItem(int position) {//获取数据集中与指定索引对应的数据项
                return null;
            }
        };


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1 = (TextView) view.findViewById(R.id.Textviewdis);//找到Textviewname
                Vehicle v1 = list.get(position);
//                Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_SHORT).show();//显示数据
                Gson gson = new Gson();
                String json = gson.toJson(v1);


                Intent it = new Intent(MainActivity.this, DetailActivity.class); //
                Bundle b = new Bundle();
                b.putString("we",json);  //string
                // b.putSerializable("dd",str);
                // it.putExtra("str_1",str);
                it.putExtras(b);
                startActivity(it);


            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson1=new Gson();
                Log.i("info","jsonstr2=="+liststr);
                list= gson1.fromJson(liststr, new TypeToken<List<Vehicle>>() {}.getType());
                if (list!=null)
                {
                    for (Vehicle v1 : list) {
//            Vehicle ue = new Vehicle();//给实体类赋值
////            ue.setDistance();
//            ue.setBattery(i);
                        vehicleList.add(v1);
                    }
                }
                lv.setAdapter(adapter);
            }

        });
        bt_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TomtomActivity.class); //
                Bundle b = new Bundle();
                b.putString("mapstr","");  //string
                // b.putSerializable("dd",str);
                // it.putExtra("str_1",str);
                it.putExtras(b);
                startActivity(it);
            }
        });

    }


}
