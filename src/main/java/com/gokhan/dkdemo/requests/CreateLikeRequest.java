package com.gokhan.dkdemo.requests;

public class CreateLikeRequest {
    private Long userId;
    private Long postId;

    public CreateLikeRequest() {
    }

    public CreateLikeRequest(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
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
