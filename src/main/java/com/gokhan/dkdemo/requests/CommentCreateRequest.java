package com.gokhan.dkdemo.requests;

public class CommentCreateRequest {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;

    public CommentCreateRequest() {
    }

    public CommentCreateRequest(Long id, String text, Long userId, Long postId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.postId = postId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
} 