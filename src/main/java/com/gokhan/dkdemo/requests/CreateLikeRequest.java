package com.gokhan.dkdemo.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {
    Long id;
    Long userId;
    Long postId;
}
