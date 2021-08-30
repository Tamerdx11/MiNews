package com.example.ahmedtawfik.lab05android;

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
                    .placeholder(R.drawable.newspaper)
                    .error(R.drawable.newspaper)
                    .into(ivBasicImage);




        }



        source_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download.setImageResource(R.drawable.downloaded);
                Toast.makeText(getApplicationContext(),"Downloaded success.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}