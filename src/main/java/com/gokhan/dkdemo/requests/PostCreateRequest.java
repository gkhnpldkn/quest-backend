package com.gokhan.dkdemo.requests;

public class PostCreateRequest {
    private Long id;
    private String text;
    private String title;
    private Long userId;

    public PostCreateRequest() {
    }

    public PostCreateRequest(Long id, String text, String title, Long userId) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
