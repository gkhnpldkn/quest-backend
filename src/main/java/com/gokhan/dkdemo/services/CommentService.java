package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.Comment;
import com.gokhan.dkdemo.entity.Post;
import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.CommentRepository;
import com.gokhan.dkdemo.requests.CommentUpdateRequest;
import com.gokhan.dkdemo.requests.CreateCommentRequest;
import com.gokhan.dkdemo.responses.CommentResponse;
import com.gokhan.dkdemo.responses.PostResponse;
import com.gokhan.dkdemo.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public List<CommentResponse> getAllCommentsWithParam(Long userId, Long postId) {
        List<Comment> commentList;
        List<CommentResponse> commentResponseList = new ArrayList<>();

        if (userId != null && postId != null) {
            commentList = commentRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId != null) {
            commentList = commentRepository.findByUserId(userId);
        } else if (postId != null) {
            commentList = commentRepository.findByPostId(postId);
        } else {
            commentList = commentRepository.findAll();
        }

        commentList.forEach(comment -> {
            CommentResponse commentResponse = getCommentResponse(comment);
            commentResponseList.add(commentResponse);
        });

        return commentResponseList;
    }

    public CommentResponse getOneComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        return getCommentResponse(comment);
    }

    public CommentResponse createNewComment(CreateCommentRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if (user != null && post != null) {
            Comment comment = Comment.builder().text(request.getText()).user(user).post(post).build();
            commentRepository.save(comment);
            return getCommentResponse(comment);
        }
        return null;
    }

    public CommentResponse updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()){
            Comment updatedComment= commentOptional.get();
            updatedComment.setText(request.getText());
            commentRepository.save(updatedComment);
            return getCommentResponse(updatedComment);
        }
        return null;
    }

    public void deleteOneCommentById(Long commentId) {
         commentRepository.deleteById(commentId);
    }

    private CommentResponse getCommentResponse(Comment comment) {
        if (comment == null) {
            return null;
        }
        User userSaved = comment.getUser();
        UserResponse userResponse = null;
        if (userSaved != null) {
            userResponse = UserResponse.builder().id(userSaved.getId()).userName(userSaved.getUserName()).password(userSaved.getPassword()).build();
        }

        Post postSaved = comment.getPost();
        PostResponse postResponse = null;
        if (postSaved != null) {
            postResponse = PostResponse.builder()
                    .id(postSaved.getId())
                    .title(postSaved.getTitle())
                    .text(postSaved.getText())
                    .userId(postSaved.getUser().getId())
                    .userName(postSaved.getUser().getUserName())
                    .build();
        }

        return CommentResponse.builder().id(comment.getId())
                .text(comment.getText())
                .postResponse(postResponse)
                .userResponse(userResponse)
                .build();
    }
}
