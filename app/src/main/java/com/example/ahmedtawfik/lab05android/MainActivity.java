package com.example.ahmedtawfik.lab05android;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<news>> {

    ListView lv_news;
    ProgressBar progressBar;
    private String URL;
    static ArrayList<news> arrayList_news = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent()!=null){
            URL = getIntent().getStringExtra("URL");
        }
        lv_news = findViewById(R.id.lv_news);
        progressBar = findViewById(R.id.pb_progress);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, MainActivity.this).forceLoad();

    }

    @NonNull
    @Override
    public Loader<ArrayList<news>> onCreateLoader(int i, @Nullable Bundle bundle) {
        progressBar.setVisibility(View.VISIBLE);

        return new StudentLoader(MainActivity.this, URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<news>> loader, final ArrayList<news> students) {

        progressBar.setVisibility(View.GONE);

        if (students.size() == 0)
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

       final NewsAdapter newsAdapter = (NewsAdapter) new NewsAdapter(getApplicationContext(), students);

        lv_news.setAdapter(newsAdapter);

        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,Full_news_data.class);
                intent.putExtra("title",students.get(i).getTitle());
                intent.putExtra("description",students.get(i).getDataNews());
                intent.putExtra("pubDate",students.get(i).getDate());
                intent.putExtra("link",students.get(i).getLink());
                intent.putExtra("image_url",students.get(i).getNewsImage());
                intent.putExtra("source_id",students.get(i).getSource_id());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<news>> loader) {
    }
}
