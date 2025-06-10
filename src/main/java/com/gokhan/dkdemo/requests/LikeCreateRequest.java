package com.gokhan.dkdemo.requests;

public class LikeCreateRequest {
    private Long id;
    private Long userId;
    private Long postId;

    public LikeCreateRequest() {
    }

    public LikeCreateRequest(Long id, Long userId, Long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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