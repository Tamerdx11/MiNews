package com.example.ahmedtawfik.lab05android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class login extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView=findViewById(R.id.start_view);
        textView=findViewById(R.id.start_text);
        // trans from login to MainActivity
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                imageView.setImageResource(R.drawable.start2);
                textView.setText(R.string.sport);
            }
        }, 3000);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                imageView.setImageResource(R.drawable.start3);
                textView.setText(R.string.entertainments);
            }
        }, 4000);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                imageView.setImageResource(R.drawable.start4);
                textView.setText(R.string.business);
            }
        }, 5000);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                imageView.setImageResource(R.drawable.start5);
                textView.setText(R.string.top_News);
            }
        }, 6000);
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                imageView.setImageResource(R.drawable.sss);
                textView.setText(R.string.In_One_App);
            }
        }, 7000);

        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                Intent intent=new Intent(login.this, categories_data.class);
                startActivity(intent);
                finish();
            }
        }, 8500);
    }
}


/*    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="@string/banner"
        android:textColor="#FF5722"
        android:textStyle="bold"
        android:textSize="35sp" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="@string/banner2"
        android:textColor="#FF5722"
        android:textStyle="bold"
        android:textSize="35sp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_gravity="center_horizontal"
        android:text="@string/banner3"
        android:textColor="#F44336"
        android:textStyle="bold"
        android:textSize="35sp" />
          <ImageView
        android:layout_width="270dp"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_gravity="center_horizontal"
        android:padding="30dp"
        android:src="@drawable/sss" />



        */



