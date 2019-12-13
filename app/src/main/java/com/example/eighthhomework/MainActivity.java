package com.example.eighthhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.eighthhomework.Recyclerview.RvAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private ArrayList<News>news = new ArrayList<>();
    private ArrayList<String>URL1 = new ArrayList<>();
    private ArrayList<String>img = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    news = (ArrayList<News>) HttpURLConnection_GET();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //实现recycrview
                        recyclerView = findViewById(R.id.Rv);
                        RvAdapter rvAdapter = new RvAdapter(news);
                        recyclerView.setAdapter(rvAdapter);
                        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                        rvAdapter.setItemClickListener(new RvAdapter.ItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Uri uri = Uri.parse(URL1.get(position));
                                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();

    }
    public List<News> HttpURLConnection_GET()throws Exception{
        ArrayList<News>list=new ArrayList<>();
        //网络请求
        URL url = new URL("http://gank.io/api/data/Android/11/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(8000);
        connection.setConnectTimeout(8000);
        if(connection.getResponseCode()==200){
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte [] buffer = new byte[1024];
            int length;
            while ((length=inputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,length);
            }
            String jsonString = byteArrayOutputStream.toString();
            inputStream.close();
            //解析过程
            JSONObject object = new JSONObject(jsonString);
            JSONArray jsonArray = object.getJSONArray("results");
            for (int i  = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                News news =new News();
                news.setTitle(jsonObject.getString("desc"));
                news.setName(jsonObject.getString("who"));
                news.setPublishTime(jsonObject.getString("publishedAt"));
                URL1.add(jsonObject.getString("url"));
                list.add(news);
            }
        }
        return list;
    }

}



