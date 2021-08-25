package com.example.ahmedtawfik.lab05android;

public class news {

    private String title;
    private String dataNews;
    private String date;
    private String newsImage;

    public news() {

    }

    public news(String title, String dataNews, String date/*, String newsImage*/) {
        this.title=title;
        this.dataNews=dataNews;
        this.date=date;
        this.newsImage=newsImage;
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

  /*  public String getNewsImg() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) { this.newsImage=newsImage; }*/

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
