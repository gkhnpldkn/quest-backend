package com.gokhan.dkdemo.requests;

public class PostUpdateRaquest {
    private String text;
    private String title;

    public PostUpdateRaquest() {
    }

    public PostUpdateRaquest(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
