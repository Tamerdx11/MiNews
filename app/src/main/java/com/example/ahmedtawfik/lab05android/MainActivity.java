package com.example.ahmedtawfik.lab05android;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<news>> {

    Button btn_showEmployees;
    ListView lv_showEmployees;
    ProgressBar progressBar;
    static ArrayList<news> arrayList_news = new ArrayList<>();

    private static String custom_url = "https://jsonware.com/json/d7556a9a2d8315ad144fa08e606a697b.json";

    private static String STUDENTS_URL = "https://jsonware.com/json/5a182107d299cb9e5a9101f0e4ddb0e3.json";
    private static String NEWS_URL = "http://api.mediastack.com/v1/news?access_key=bb82f4094db45839054ba3176d02a9f6";
    private static String NEWS_URL_EG ="https://newsapi.org/v2/everything?q=Apple&from=2021-08-25&sortBy=popularity&apiKey=b11a2c06db824342bc77ce5660410285";
    //ACCESS_KEY(NEWS_URL_EG) = b11a2c06db824342bc77ce5660410285
    // ACCESS_KEY(NEWS_URL) = bb82f4094db45839054ba3176d02a9f6

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_showEmployees = findViewById(R.id.lv_showEmployees);
       // btn_showEmployees = findViewById(R.id.btn_showEmployees);
        progressBar = findViewById(R.id.pb_progress);


        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, MainActivity.this).forceLoad();

       /* btn_showEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList_news.add(new news("title","NEWS","12/8/2021"));
                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.initLoader(0, null, MainActivity.this).forceLoad();
            }
        });*/

    }

    @NonNull
    @Override
    public Loader<ArrayList<news>> onCreateLoader(int i, @Nullable Bundle bundle) {
        progressBar.setVisibility(View.VISIBLE);
        return new StudentLoader(MainActivity.this, custom_url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<news>> loader, ArrayList<news> students) {

        progressBar.setVisibility(View.GONE);

        if (students.size() == 0)
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

       final NewsAdapter newsAdapter = (NewsAdapter) new NewsAdapter(getApplicationContext(), /*R.layout.list_item_new,*/ students);

        lv_showEmployees.setAdapter(newsAdapter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<news>> loader) {
    }
}
