package com.gokhan.dkdemo.requests;

import lombok.Data;

@Data
public class CreateCommentRequest {
    Long userId;
    Long postId;
    String text;
}
