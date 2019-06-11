package com.bawei.gaochenkai20190611.vi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.bawei.gaochenkai20190611.R;
import com.bawei.gaochenkai20190611.adapter.MyXListviewAdapter;
import com.bawei.gaochenkai20190611.bean.JsonBean;
import com.bawei.gaochenkai20190611.data.HttpUtil;
import com.qy.xlistview.XListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.zip.Adler32;

public class MainActivity extends AppCompatActivity {

    private XListView xlv;
    private String str = "https://www.apiopen.top/satinApi";
    private HttpUtil util = HttpUtil.getUtil();
    private ArrayList<JsonBean> list = new ArrayList<>();
    private MyXListviewAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断网络
        boolean netWork = util.isNetWork(MainActivity.this);
        if (netWork){
            Toast.makeText(MainActivity.this,"有网",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"没网",Toast.LENGTH_SHORT).show();
            getSp();
            return;
        }

        //获取控件id
        xlv = findViewById(R.id.xlv);
        xlv.stopLoadMore();
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() { //上拉刷新
                getData();
                onRestart();
            }

            @Override
            public void onLoadMore() { //下拉加载
                getData();
                onLoadMore();
            }

//            MyXListviewAdapter adapter = new MyXListviewAdapter(list,MainActivity.this);

        });

        getData();

    }
    //sp存储
    private void getSp() {

    }

    //xlistview展示
    private void getData() {
        util.getAsyncTask(str, new HttpUtil.CCallBackString() {
            @Override
            public void getbackstring(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject obj = (JSONObject) data.get(i);
                        String profile_image = obj.getString("profile_image");
                        String screen_name = obj.getString("screen_name");
                        String passtime = obj.getString("passtime");
                        String text = obj.getString("text");
                        list.add(new JsonBean(profile_image,screen_name,passtime,text));
                    }
                    xlv.setAdapter(new MyXListviewAdapter(list,MainActivity.this));
                    adapter.notifyDataSetChanged(); //刷新适配器
                    xlv.computeScroll();//刷新xlistview

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
