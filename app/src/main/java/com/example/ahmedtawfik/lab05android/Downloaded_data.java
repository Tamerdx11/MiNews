package com.example.ahmedtawfik.lab05android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Downloaded_data extends AppCompatActivity {
    ListView lv_download;

    ArrayList<String> news_d=new ArrayList<>();

    private String title,description,pubDate;

    DatabaseController DbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbController=new DatabaseController(getApplicationContext());

        setContentView(R.layout.activity_downloaded_data);
        lv_download=findViewById(R.id.lv_download);


        DbController.open();
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.list_item_download,news_d);
        lv_download.setAdapter(adapter);
        ///get intent
        if(getIntent()!=null){
            title=getIntent().getStringExtra("title");
            description=getIntent().getStringExtra("description");
            pubDate=getIntent().getStringExtra("pubDate");

            DbController.insertDetails(title,description,pubDate);
            //get_news_d();
            adapter.notifyDataSetChanged();

        }

        for (Details details : DbController.selectdetails()) {
            news_d.add(details.toString());
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();

        lv_download.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Downloaded_data.this);

                builder.setTitle("Confirmation Dialog").setMessage("Would you like to delete " + DbController.selectdetails().get(position).getTitle());

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DbController.deletedetails(DbController.selectdetails().get(position).get_detailsId());
                        get_news_d();
                        adapter.notifyDataSetChanged();
                    }
                });
                adapter.notifyDataSetChanged();
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });



    }

    private  void get_news_d(){
        news_d.clear();
        for (Details details : DbController.selectdetails()) {
            news_d.add(details.toString());
        }

    }
        protected void onDestroy() {
        super.onDestroy();
        DbController.close();
    }
}