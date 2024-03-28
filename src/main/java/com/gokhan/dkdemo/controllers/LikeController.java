package com.gokhan.dkdemo.controllers;

import com.gokhan.dkdemo.requests.CreateLikeRequest;
import com.gokhan.dkdemo.responses.LikeResponse;
import com.gokhan.dkdemo.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLike(@RequestParam Long userId, @RequestParam Long postId){
        return likeService.getAllLikesWithParam(userId, postId);
    }
    @GetMapping("/{likeId}")
    public LikeResponse getLikeById(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping
    public  LikeResponse createLike(@RequestBody CreateLikeRequest request){
        return likeService.createOneLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLikeById(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
