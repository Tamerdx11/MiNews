package com.example.ahmedtawfik.lab05android;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        TextView tv_item_date=currentListView.findViewById(R.id.tv_item_date);
        tv_item_date.setText(currentUser.getDate());

        TextView tv_item_description=currentListView.findViewById(R.id.tv_item_description);
        tv_item_description.setText(currentUser.getDataNews());

        TextView tv_item_source=currentListView.findViewById(R.id.tv_item_source);
        tv_item_source.setText(currentUser.getSource_id());

        ImageView iv_save=currentListView.findViewById(R.id.iv_save);
        iv_save.setImageResource(currentUser.getIv());

        ///download image with Picasso
        String imageUri = currentUser.getNewsImage();
        ImageView iv_userImage=currentListView.findViewById(R.id.iv_image);
        Picasso.with(getContext()).load(imageUri).fit().centerCrop()
                .placeholder(R.drawable.mi_icon8)
                .error(R.drawable.mi_icon8)
                .into(iv_userImage);

        return currentListView;
    }
}
