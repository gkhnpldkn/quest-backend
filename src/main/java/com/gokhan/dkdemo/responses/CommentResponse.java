package com.gokhan.dkdemo.responses;

public class CommentResponse {
    private Long id;
    private String text;
    private UserResponse userResponse;
    private PostResponse postResponse;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String text, UserResponse userResponse, PostResponse postResponse) {
        this.id = id;
        this.text = text;
        this.userResponse = userResponse;
        this.postResponse = postResponse;
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
