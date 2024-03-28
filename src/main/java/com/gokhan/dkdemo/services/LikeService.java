package com.gokhan.dkdemo.services;

import com.gokhan.dkdemo.entity.Like;
import com.gokhan.dkdemo.entity.Post;
import com.gokhan.dkdemo.entity.User;
import com.gokhan.dkdemo.repos.LikeRepository;
import com.gokhan.dkdemo.requests.CreateLikeRequest;
import com.gokhan.dkdemo.responses.LikeResponse;
import com.gokhan.dkdemo.responses.PostResponse;
import com.gokhan.dkdemo.responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public List<LikeResponse> getAllLikesWithParam(Long userId, Long postId) {
        List<Like> likeList;
        List<LikeResponse> likeResponseList = new ArrayList<>();


        if (userId != null && postId != null) {
            likeList = likeRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId != null) {
            likeList = likeRepository.findByUserId(userId);
        } else if (postId != null) {
            likeList = likeRepository.findByPostId(postId);
        } else {
            likeList = likeRepository.findAll();
        }

        likeList.forEach(like -> {
            LikeResponse likeResponse = getLikeResponse(like);
            likeResponseList.add(likeResponse);
        });

        return likeResponseList;
    }


    public LikeResponse getOneLikeById (Long LikeId) {
        Like like = likeRepository.findById(LikeId).orElse(null);

        LikeResponse likeResponse = getLikeResponse(like);
        return likeResponse;
    }


    public LikeResponse createOneLike (CreateLikeRequest request){
            User user = userService.getUserById(request.getUserId());
            Post post = postService.getPostById(request.getPostId());

            if (user == null && post == null) {
                return null;
            }

            Like.LikeBuilder likeBuilder = Like.builder();

            if (user != null) {
                likeBuilder.user(user);
            }
            if (post != null) {
                likeBuilder.post(post);
            }
            Like like = likeBuilder.build();
            likeRepository.save(like);

            return getLikeResponse(like);
        }

        public void deleteOneLikeById (Long likeId){
            likeRepository.deleteById(likeId);
        }

    private LikeResponse getLikeResponse(Like like) {
        if (like == null) {
            return null;
        }
        User userSaved = like.getUser();
        UserResponse userResponse = null;
        if (userSaved != null) {
            userResponse = UserResponse.builder().id(userSaved.getId()).userName(userSaved.getUserName()).password(userSaved.getPassword()).build();
        }

        Post postSaved = like.getPost();
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

        return LikeResponse.builder().id(like.getId())
                .postResponse(postResponse)
                .userResponse(userResponse)
                .build();
    }
}
