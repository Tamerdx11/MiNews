package com.example.ahmedtawfik.lab05android;

import android.widget.ImageView;

public class news {

    private String title;
    private String dataNews;
    private String date;
    private String newsImage;
    private String link;
    private String source_id;
    int iv;

    public news() { }

    public news(String title, String description, String pubDate,String link, String image_url, String source_id,int iv) {
        this.title=title;
        this.dataNews=description;
        this.date=pubDate;
        this.link=link;
        this.newsImage=image_url;
        this.source_id=source_id;
        this.iv=iv;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getDataNews() {
        return dataNews;
    }

    public void setDataNews(String dataNews) {
        this.dataNews=dataNews;
    }

   public String getNewsImg() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) { this.newsImage=newsImage; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "news{" +
                "title='" + title + '\'' +
                ", dataNews='" + dataNews + '\'' +
                ", date='" + date + '\'' +
                ", newsImage='" + newsImage + '\'' +
                '}';
    }

}
