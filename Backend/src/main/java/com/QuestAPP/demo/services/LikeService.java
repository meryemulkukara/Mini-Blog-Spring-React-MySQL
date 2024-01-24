package com.QuestAPP.demo.services;

import com.QuestAPP.demo.entities.Like;
import com.QuestAPP.demo.entities.Post;
import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.repositories.LikeRepository;
import com.QuestAPP.demo.requests.LikeCreateRequest;
import com.QuestAPP.demo.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    LikeRepository likeRepository;
    UserService userService;
    PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> userid, Optional<Long> postid) {
        List<Like> likes;
        if (userid.isPresent()&& postid.isPresent()) {
            likes=likeRepository.findByUserIdAndPostId(userid.get(), postid.get());
        }
        else if( userid.isPresent()){   likes= likeRepository.findByUserId(userid.get());}
        else if( postid.isPresent()){   likes= likeRepository.findByPostId(postid.get());}
        else {    likes= likeRepository.findAll();        }

        return likes.stream().map(LikeResponse::new).collect(Collectors.toList());
    }



    public Like getOneLikeById(Long likeid) {
        return likeRepository.findById(likeid).orElse(null);
    }


    public void deleteOneLike(Long likeid) {        likeRepository.deleteById(likeid);    }

    public Like createOneLike(LikeCreateRequest newlike) {

        if(userService.getOneUserById(newlike.getUserid())==null || postService.getOnePostById(newlike.getPostid())==null
        //|| likeRepository.findByUserIdAndPostId(newlike.getPostid(), newlike.getUserid()) != null
        ){
            return null;
        }
        else
        {   Like like= new Like();
            Post post=postService.getOnePostById(newlike.getPostid());
            like.setId(newlike.getId());
            like.setPost(post);
            like.setUser(userService.getOneUserById(newlike.getUserid()));
            return likeRepository.save(like);}
    }


}

