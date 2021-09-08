package com.example.ahmedtawfik.lab05android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Full_news_data extends AppCompatActivity {
    ImageView mainImage,download;
    TextView title, description,pubDate,source,source_id;
    private String news_url;
    ///
    private static String title_d,description_d,pubDate_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news_data);

        mainImage=findViewById(R.id.iv_mainImage);
        download=findViewById(R.id.iv_download);
        title=findViewById(R.id.tv_title);
        description=findViewById(R.id.tv_description);
        pubDate=findViewById(R.id.tv_date);
        source_id=findViewById(R.id.tv_source_id);

        if(getIntent()!=null){
            title.setText(getIntent().getStringExtra("title"));
            description.setText(getIntent().getStringExtra("description"));
            pubDate.setText(getIntent().getStringExtra("pubDate"));
            news_url=getIntent().getStringExtra("link");
            source_id.setText(getIntent().getStringExtra("source_id"));
            ///download image with Picasso
             String imageUri = getIntent().getStringExtra("image_url");
             ImageView ivBasicImage = (ImageView) findViewById(R.id.iv_mainImage);
            Picasso.with(getApplicationContext()).load(imageUri).fit().centerCrop()
                    .placeholder(R.drawable.news_icon2)
                    .error(R.drawable.news_icon2)
                    .into(ivBasicImage);

            ///// press to source:(/* button */) show data in (Google)
            source_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoUrl(news_url = getIntent().getStringExtra("link"));
                }
                private void gotoUrl(String s) {
                    Uri uri = Uri.parse(s);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            });

            ///data for downloading
            title_d=getIntent().getStringExtra("title");
            description_d=getIntent().getStringExtra("description");
            pubDate_d=getIntent().getStringExtra("pubDate");
        }

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download.setImageResource(R.drawable.downloaded);
                Toast.makeText(getApplicationContext(),"Downloaded success.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Full_news_data.this,Downloaded_data.class);
                intent.putExtra("title", title_d);
                intent.putExtra("description", description_d);
                intent.putExtra("pubDate",pubDate_d );
                startActivity(intent);
            }
        });
    }
}