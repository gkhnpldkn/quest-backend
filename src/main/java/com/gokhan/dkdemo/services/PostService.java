package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.Post;
import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.PostRepository;
import com.gokhan.dkdemo.requests.PostCreateRequest;
import com.gokhan.dkdemo.requests.PostUpdateRaquest;
import com.gokhan.dkdemo.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public List<PostResponse> getAllPosts(Long userId) {
        List<PostResponse> postResponseList = new ArrayList<>();
        List<Post> postList;
        if (userId != null) {
            postList = postRepository.findByUserId(userId);
        } else {
            postList = postRepository.findAll();
        }

        postList.forEach(post -> {
            PostResponse postResponse = PostResponse.builder()
                    .userId(post.getUser().getId())
                    .id(post.getId())
                    .title(post.getTitle())
                    .userName(post.getUser().getUserName())
                    .text(post.getText())
                    .build();
            postResponseList.add(postResponse);
        });
        return postResponseList;
    }

    public PostResponse getOnePost(Long postId) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }

        PostResponse postResponse = PostResponse.builder()
                .userId(post.getId())
                .id(post.getId())
                .title(post.getTitle())
                .userName(post.getUser().getUserName())
                .text(post.getText())
                .build();
        return postResponse;
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
        Post post = Post.builder().text(newPostRequest.getText()).title(newPostRequest.getTitle()).user(user).build();
        postRepository.save(post);
        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .title(post.getTitle()).text(post.getText())
                .userName(post.getUser().getUserName())
                .build();
    }

    public PostResponse updateOnePost(Long postId, PostUpdateRaquest postUpdateRaquest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            PostResponse postResponse = PostResponse.builder().id(post.get().getId())
                    .title(postUpdateRaquest.getTitle())
                    .text(postUpdateRaquest.getText())
                    .userName(post.get().getUser().getUserName())
                    .userId(post.get().getUser().getId()).build();
            return postResponse;
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
