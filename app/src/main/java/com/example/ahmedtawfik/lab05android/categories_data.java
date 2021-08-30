package com.example.ahmedtawfik.lab05android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class categories_data extends AppCompatActivity {

    private static String NEWS_EG ="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151";
    private static String NEWS_SPORTS_EG="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151&category=sports&country=eg";
    private static String NEWS_TOP_EG="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151&category=top&country=eg";
    private static String NEWS_BUSINESS_EG="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151&category=business&country=eg";
    private static String NEWS_SCIENCE_EG="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151&category=science&country=eg";
    private static String NEWS_HEALTH_EG="https://newsdata.io/api/1/news?apikey=pub_1021e64c7f637d7610845ede96633b0f4151&category=health&country=eg";

    ImageView health, top, sports, sciences, business, downloads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_data);
        health=findViewById(R.id.health_news);
        top=findViewById(R.id.top_news);
        sports=findViewById(R.id.sport_news);
        sciences=findViewById(R.id.science_news);
        business=findViewById(R.id.business_news);
        downloads=findViewById(R.id.downloads);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categories_data.this,MainActivity.class);
                intent.putExtra("URL",NEWS_HEALTH_EG);
                startActivity(intent);

            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categories_data.this,MainActivity.class);
                intent.putExtra("URL",NEWS_TOP_EG);
                startActivity(intent);

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categories_data.this,MainActivity.class);
                intent.putExtra("URL",NEWS_SPORTS_EG);
                startActivity(intent);

            }
        });
        sciences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categories_data.this,MainActivity.class);
                intent.putExtra("URL",NEWS_SCIENCE_EG);
                startActivity(intent);

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categories_data.this,MainActivity.class);
                intent.putExtra("URL",NEWS_BUSINESS_EG);
                startActivity(intent);

            }
        });
        downloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}