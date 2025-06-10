package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.Post;
import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.PostRepository;
import com.gokhan.dkdemo.requests.PostCreateRequest;
import com.gokhan.dkdemo.requests.PostUpdateRaquest;
import com.gokhan.dkdemo.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Long userId) {
        List<PostResponse> postResponseList = new ArrayList<>();
        List<Post> postList;
        if (userId != null) {
            postList = postRepository.findByUserId(userId);
        } else {
            postList = postRepository.findAll();
        }

        postList.forEach(post -> {
            PostResponse postResponse = new PostResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUserName(),
                post.getTitle(),
                post.getText()
            );
            postResponseList.add(postResponse);
        });
        return postResponseList;
    }

    public PostResponse getOnePost(Long postId) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }

        return new PostResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getUserName(),
            post.getTitle(),
            post.getText()
        );
    }

    public Post getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return post;
        }
        return null;
    }

    public PostResponse createNewPost(PostCreateRequest newPostRequest) {
        if (newPostRequest.getUserId() == null) {
            throw new RuntimeException("Userın id si dolu olmalıdır!!");
        }
        User user = userService.getUserById(newPostRequest.getUserId());
        Post post = new Post();
        post.setText(newPostRequest.getText());
        post.setTitle(newPostRequest.getTitle());
        post.setUser(user);
        postRepository.save(post);
        return new PostResponse(
            post.getId(),
            post.getUser().getId(),
            post.getUser().getUserName(),
            post.getTitle(),
            post.getText()
        );
    }

    public PostResponse updateOnePost(Long postId, PostUpdateRaquest postUpdateRaquest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return new PostResponse(
                post.get().getId(),
                post.get().getUser().getId(),
                post.get().getUser().getUserName(),
                postUpdateRaquest.getTitle(),
                postUpdateRaquest.getText()
            );
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
