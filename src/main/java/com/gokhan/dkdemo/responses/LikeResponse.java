package com.gokhan.dkdemo.responses;

public class LikeResponse {
    private Long id;
    private UserResponse userResponse;
    private PostResponse postResponse;

    public LikeResponse() {
    }

    public LikeResponse(Long id, UserResponse userResponse, PostResponse postResponse) {
        this.id = id;
        this.userResponse = userResponse;
        this.postResponse = postResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public PostResponse getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(PostResponse postResponse) {
        this.postResponse = postResponse;
    }
}
