package com.gokhan.dkdemo.requests;

public class CreateCommentRequest {
    private Long userId;
    private Long postId;
    private String text;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(Long userId, Long postId, String text) {
        this.userId = userId;
        this.postId = postId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
