package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.Post;
import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.PostRepository;
import com.gokhan.dkdemo.repos.UserRepository;
import com.gokhan.dkdemo.requests.PostCreateRequest;
import com.gokhan.dkdemo.requests.PostUpdateRaquest;
import com.gokhan.dkdemo.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public List<PostResponse> getAllPosts(Long userId) {
        List<Post> list;
        if (userId != null) {
            list = postRepository.findByUserId(userId);
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
    }

    public PostResponse getOnePost(Long postId) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }

        PostResponse postResponse = PostResponse.builder().userId(post.getId())
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
        Optional<User> user = userRepository.findById(newPostRequest.getId());
        if (user == null) {
            return null;
        } else {
            Post post = new Post();
            post.setId(newPostRequest.getId());
            post.setTitle(newPostRequest.getTitle());
            post.setText(newPostRequest.getText());
            post.setUser(user.get());
            PostResponse postResponse = PostResponse.builder().title(newPostRequest.getTitle()).text(newPostRequest.getText())
                    .userName(post.getUser().getUserName()).build();
            return postResponse;
        }


    }

    public PostResponse updateOnePost(Long postId, PostUpdateRaquest postUpdateRaquest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            PostResponse postResponse = PostResponse.builder().id(post.get().getId()).title(postUpdateRaquest.getTitle())
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


