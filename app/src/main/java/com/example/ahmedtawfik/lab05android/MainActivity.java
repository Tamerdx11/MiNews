package com.example.ahmedtawfik.lab05android;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<news>> {
    private static int visibility_country=0, visibility_categories=0,like_click=0,s=0;

    LinearLayout ll_countries,ll_categories,ll_no_internet;
    ImageView iv_country,iv_save;
    TextView tv_categories,txt_countries,tv_item_source;
    ListView lv_news;
    ProgressBar progressBar;
    EditText search;
    private String URL;
    private static String URL_link;
    private static String Description;
    private static String Date;
    private static String Title;
   // DatabaseController DbController;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_country=findViewById(R.id.iv_country);
        iv_save=findViewById(R.id.iv_save);
        tv_categories=findViewById(R.id.tv_categories);
        ll_countries=findViewById(R.id.ll_countries);
        ll_categories=findViewById(R.id.ll_categories);
        ll_no_internet=findViewById(R.id.ll_no_internet);
        search=findViewById(R.id.ed_search);
        txt_countries=findViewById(R.id.txt_countries);
        tv_item_source=findViewById(R.id.tv_item_source);
        ////data base
      // DbController=new DatabaseController(getApplicationContext());
       // DbController.open();
        ///countries_visibility
        iv_country.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(visibility_country==0){
                    ll_countries.setVisibility(View.VISIBLE);
                    txt_countries.setTextColor(Color.BLUE);
                    visibility_country=1;
                }
                else{
                    ll_countries.setVisibility(View.GONE);
                    txt_countries.setTextColor(Color.BLACK);
                    visibility_country=0;
                }
            }
        });
        ///categories_visibility
        tv_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visibility_categories==0){
                    ll_categories.setVisibility(View.VISIBLE);
                    visibility_categories=1;
                    tv_categories.setText(R.string.tv_categories2);
                    tv_categories.setTextColor(Color.BLUE);
                }
                else{
                    if(visibility_country==1){
                        visibility_country=0;
                        ll_countries.setVisibility(View.GONE);
                        txt_countries.setTextColor(Color.BLACK);
                    }
                    ll_categories.setVisibility(View.GONE);
                    visibility_categories=0;
                    tv_categories.setText(R.string.tv_categories);
                    tv_categories.setTextColor(Color.BLACK);
                }
            }
        });

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
    public void onLoadFinished(@NonNull final Loader<ArrayList<news>> loader, final ArrayList<news> students) {

        progressBar.setVisibility(View.GONE);

        if (students.size() == 0){
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            ll_no_internet.setVisibility(View.VISIBLE);
        }
        else
            ll_no_internet.setVisibility(View.GONE);

       final NewsAdapter newsAdapter = (NewsAdapter) new NewsAdapter(getApplicationContext(), students);
        lv_news.setAdapter(newsAdapter);


        /////
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
            /////
               URL_link=students.get(i).getLink();
               Title=students.get(i).getTitle();
               Date=students.get(i).getDate();
               Description=students.get(i).getDataNews();
               ///////
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(" Date: "+Date).setMessage(Title + ".");
                builder.setPositiveButton(R.string.More_Details, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoUrl(URL_link);
                    }
                    private void gotoUrl(String s) {
                        Uri uri = Uri.parse(s);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }
                });
               if(like_click==0)
                    s=R.string.Save;
                else
                    s=R.string.unSave;

               builder.setNegativeButton(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(like_click==0){
                            students.get(i).setIv(R.drawable.like);
                            like_click=1;
                            Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                        }else{
                            students.get(i).setIv(R.drawable.unlove);
                            like_click=0;
                            Toast.makeText(getApplicationContext(),"unsaved",Toast.LENGTH_SHORT).show();
                        }
                        newsAdapter.notifyDataSetChanged();

                    }
                });
               builder.setIcon(R.drawable.news_icon2);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }
    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<news>> loader) {
    }
}
