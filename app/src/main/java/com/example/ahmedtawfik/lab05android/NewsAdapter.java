package com.example.ahmedtawfik.lab05android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<news> {

    public NewsAdapter(Context context, ArrayList<news> users) {
        super(context, 0, users);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentListView = convertView;

        if (currentListView == null) {
            currentListView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_new, parent, false);
        }

        news currentUser = getItem(position);

        TextView tv_title=currentListView.findViewById(R.id.tv_item_title);
        tv_title.setText(currentUser.getTitle());

        TextView tv_item_news=currentListView.findViewById(R.id.tv_item_news);
        tv_item_news.setText(currentUser.getDataNews());

        TextView tv_item_date=currentListView.findViewById(R.id.tv_item_date);
        tv_item_date.setText(currentUser.getDate());

       /* ImageView iv_userImage=currentListView.findViewById(R.id.iv_image);
        iv_userImage.setImageResource(currentUser.get( ));*/


        return currentListView;
    }
}
