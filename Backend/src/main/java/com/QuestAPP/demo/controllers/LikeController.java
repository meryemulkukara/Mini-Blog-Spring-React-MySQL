package com.QuestAPP.demo.controllers;


import com.QuestAPP.demo.entities.Like;
import com.QuestAPP.demo.requests.LikeCreateRequest;
import com.QuestAPP.demo.responses.LikeResponse;
import com.QuestAPP.demo.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    LikeService likeService;

    public LikeController( LikeService likeService){
        this.likeService=likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userid, @RequestParam Optional<Long> postid){
        return likeService.getAllLikes(userid, postid);
    }


    @PostMapping
    public  Like createOneLike(@RequestBody LikeCreateRequest newlike){
        return likeService.createOneLike(newlike);
    }


    @GetMapping("/{likeid}")
    public Like getOneLikeById(@PathVariable Long likeid){
        return likeService.getOneLikeById(likeid);
    }

    @DeleteMapping("/{likeid}")
    public void deleteOneLike(@PathVariable Long likeid){
        likeService.deleteOneLike(likeid);
    }
}
