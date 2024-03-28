package com.gokhan.dkdemo.controllers;

import com.gokhan.dkdemo.requests.PostCreateRequest;
import com.gokhan.dkdemo.requests.PostUpdateRaquest;
import com.gokhan.dkdemo.responses.PostResponse;
import com.gokhan.dkdemo.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Long userId){
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public PostResponse getOnePost(@PathVariable Long postId){
        return postService.getOnePost(postId);
    }
    @PostMapping
    public PostResponse createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return  postService.createNewPost(newPostRequest);
    }
    @PutMapping("/{postId}")
    public  PostResponse updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRaquest postUpdateRaquest){
        return postService.updateOnePost(postId, postUpdateRaquest);
    }
    @DeleteMapping("/{postId}")
public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}
