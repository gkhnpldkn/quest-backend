package com.gokhan.dkdemo.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    private Long id;
    private UserResponse userResponse;
    private PostResponse postResponse;
}
