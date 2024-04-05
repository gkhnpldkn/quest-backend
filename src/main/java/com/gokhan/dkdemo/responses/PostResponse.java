package com.gokhan.dkdemo.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
}
