package com.example.ahmedtawfik.lab05android;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>> {

    Button btn_showEmployees;
    ListView lv_showEmployees;
    MediaPlayer player;
    ProgressBar progressBar;
    static ArrayList<String> students = new ArrayList<>();

    private static String STUDENTS_URL = "https://jsonware.com/json/5a182107d299cb9e5a9101f0e4ddb0e3.json";

    /*static String jsonString = "{\"university\":\"Benha\",\"faculty\":\"BFCAI\",\"students\"" +
            ":[{\"id\":1,\"name\":\"Ahmed Mohamed\",\"age\":25,\"email\":\"ahmed.mohamed@mail.com\"" +
            ",\"phone\":\"01212345678\"},{\"id\":2,\"name\":\"Ahmed Ali\",\"age\":20,\"email\"" +
            ":\"ahmed.ali@mail.com\",\"phone\":\"01212340000\"},{\"id\":3,\"name\":\"Mahmoud Mohamed" +
            "\",\"age\":27,\"email\":\"mahmoud.mohamed@mail.com\",\"phone\":\"01211115678\"},{\"id\":4," +
            "\"name\":\"Hesham Mohamed\",\"age\":24,\"email\":\"ahmed.mohamed@mail.com\",\"phone\":" +
            "\"01212345678\"},{\"id\":5,\"name\":\"Samy Mohamed\",\"age\":25,\"email\":\"ahmed.mohamed@mail.com" +
            "\",\"phone\":\"01212345678\"}]}";*/


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_showEmployees = findViewById(R.id.lv_showEmployees);
        btn_showEmployees = findViewById(R.id.btn_showEmployees);
        progressBar = findViewById(R.id.pb_progress);


        btn_showEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.initLoader(0, null, MainActivity.this).forceLoad();



/*                try {
                    JSONObject jsonRoot = new JSONObject(jsonString);

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

                    studentsAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */

            }
        });


        //player = MediaPlayer.create(MainActivity.this, R.raw.railroad);

    }

    @NonNull
    @Override
    public Loader<ArrayList<String>> onCreateLoader(int i, @Nullable Bundle bundle) {
        progressBar.setVisibility(View.VISIBLE);
        return new StudentLoader(MainActivity.this, STUDENTS_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<String>> loader, ArrayList<String> students) {

        progressBar.setVisibility(View.GONE);

        if (students.size() == 0)
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> studentsAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, students);


        lv_showEmployees.setAdapter(studentsAdapter);

       //studentsAdapter.clear();

        //studentsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<String>> loader) {
        //students.clear();
    }
}
