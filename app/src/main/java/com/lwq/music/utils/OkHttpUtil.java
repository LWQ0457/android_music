package com.lwq.music.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {


    public void get(String url, Handler handler) {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);

            Response response;
            String data;
            try {
                response = call.execute();
                data= response.body().string();
                Bundle bundle=new Bundle();
                bundle.putString("data",data);
                Log.d("run: ", data);
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();


    }

    private ArrayList<Map<String,Object>> jsonJXDate(String date) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        if (date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date);

                JSONArray jsonArray = jsonObject.getJSONArray("banners");
                //遍历数组
                for (int i = 0; i < jsonArray.length(); i++) {
                    //将字段的值遍历并转型
                    String title = jsonArray.getJSONObject(i).getString("typeTitle");
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    String song = jsonArray.getJSONObject(i).getString("song");
//放入集合中，根据需要是否做下面的操作
                    Map<String, Object> map = new HashMap<>();
                    map.put("title", title);
                    map.put("pic", pic);
                    map.put("song", song);
                    list.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
