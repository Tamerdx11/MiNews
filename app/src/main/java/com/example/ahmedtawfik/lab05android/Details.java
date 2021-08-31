package com.example.ahmedtawfik.lab05android;public class Details {
    /*public final static String TABLE01_NAME="details";
    public final static String TABLE01_COL01="_detailsId";
    public final static String TABLE01_COL02="title";
    public final static String TABLE01_COL03="description";
    public final static String TABLE01_COL04="link";*/
    private int _detailsId;
    private String title,description,link;

    public Details(int anInt, String string) {
    }

    public Details(int _detailsId, String title, String description, String link) {
        this._detailsId = _detailsId;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public int get_detailsId() {
        return _detailsId;
    }

    public void set_detailsId(int _detailsId) {
        this._detailsId = _detailsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "details{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
