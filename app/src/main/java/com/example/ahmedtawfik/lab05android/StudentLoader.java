package com.example.ahmedtawfik.lab05android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class StudentLoader extends AsyncTaskLoader<ArrayList<String>> {

    String url = null;
    ArrayList<String> students = new ArrayList<>();

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
    public ArrayList<String> loadInBackground() {


        JSONObject jsonRoot = null;

        try {
            jsonRoot = new JSONObject(getHttpRequest(new URL(url)));

            JSONArray jsonArray = jsonRoot.getJSONArray("students");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = jsonArray.getJSONObject(i);

                int id = student.getInt("id");
                String name = student.getString("name");
                int age = student.getInt("age");
                String email = student.getString("email");
                String phone = student.getString("phone");

                students.add("Id: " + id + "\nName: " + name + "\nAge: " + age + "\nEmail " + email + "\nPhone: " + phone);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return students;
    }
}
