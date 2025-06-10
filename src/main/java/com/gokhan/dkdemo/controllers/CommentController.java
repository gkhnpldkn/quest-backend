package com.gokhan.dkdemo.controllers;

import com.gokhan.dkdemo.requests.CommentUpdateRequest;
import com.gokhan.dkdemo.requests.CreateCommentRequest;
import com.gokhan.dkdemo.services.CommentService;
import org.springframework.web.bind.annotation.*;
import com.gokhan.dkdemo.responses.CommentResponse;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComment(@RequestParam Long userId, @RequestParam Long postId) {
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public CommentResponse getOneComment(@PathVariable Long commentId) {
        return commentService.getOneComment(commentId);
    }

    @PostMapping
    public CommentResponse createNewComment(@RequestBody CreateCommentRequest request) {
        return commentService.createNewComment(request);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        commentService.deleteOneCommentById(commentId);
    }
}
