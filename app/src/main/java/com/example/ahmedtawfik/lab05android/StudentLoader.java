package com.example.ahmedtawfik.lab05android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class StudentLoader extends AsyncTaskLoader<ArrayList<news>> {

    String url = null;
    ArrayList<news> arrayList_news = new ArrayList<>();

    public StudentLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    private String getHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        HttpsURLConnection urlConnection = null;

        InputStream inputStream = null;

        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);

        } catch (Exception e) {
            return "No Internet Connection";
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (inputStream != null)
                inputStream.close();
        }


        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream)throws IOException {

        StringBuilder result=new StringBuilder();

        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));

            BufferedReader reader=new BufferedReader(inputStreamReader);

            String line=reader.readLine();

            while (line!=null){
                result.append(line);
                line=reader.readLine();
            }

        }
        return result.toString();
    }

    @Override
    public ArrayList<news> loadInBackground() {


        JSONObject jsonRoot = null;

        try {
            jsonRoot = new JSONObject(getHttpRequest(new URL(url)));

            JSONArray jsonArray = jsonRoot.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = jsonArray.getJSONObject(i);

                String title = student.getString("title");
                String description = student.getString("description");
                String pubDate = student.getString("pubDate");
                String link=student.getString("link");
                String image_url=student.getString("image_url");
                String source_id=student.getString("source_id");

                arrayList_news.add(new news(title,description,pubDate,link,image_url,source_id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList_news;
    }
}
