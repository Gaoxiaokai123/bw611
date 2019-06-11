package com.bawei.gaochenkai20190611.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: 高晨凯
 * @Date: 2019/6/11 09:19:34
 * @Description: GET请求方式网络工具类
 */
public class HttpUtil {
    //懒汉式单例模式
    private static HttpUtil util;

    private HttpUtil() {
    }

    public static HttpUtil getUtil() {
        if (util == null){
            return util = new HttpUtil();
        }
        return util;
    }

    //判断网络
    public boolean isNetWork(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null){
            return info.isAvailable();
        }
        return false;
    }

    //get请求
    public String HttpGet(String strurl){
        try {
            URL url = new URL(strurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); //请求方式
            connection.setConnectTimeout(5000); //请求超时时间
            connection.setReadTimeout(5000); //读取超时时间
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK){
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String str = "";
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine())!=null){
                    buffer.append(str);
                }
                reader.close();//关流
                stream.close();
                connection.disconnect();
                return buffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //接口
    public interface CCallBackString{
        void getbackstring(String s);
    }

    public void getAsyncTask(String url, final CCallBackString backString){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return HttpGet(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                backString.getbackstring(s);
            }
        }.execute(url);
    }
}

