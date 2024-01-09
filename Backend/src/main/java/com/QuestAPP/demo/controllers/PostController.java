package com.QuestAPP.demo.controllers;

import com.QuestAPP.demo.entities.Post;
import com.QuestAPP.demo.requests.PostCreateRequest;
import com.QuestAPP.demo.requests.PostUpdateRequest;
import com.QuestAPP.demo.responses.PostResponse;
import com.QuestAPP.demo.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    public PostController( PostService postService){
        this.postService=postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> user_id){
        return postService.getAllPosts(user_id);
    }

    @PostMapping
    //public Post createOnePost(@RequestBody Post newPost){        return postService.createOnePost(newPost);    }
    public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createOnePost(newPostCreateRequest);    }

    @GetMapping("/{postid}")
    public Post getOnePostById(@PathVariable Long postid ){
        return postService.getOnePostById(postid);
    }

    @PutMapping("/{postid}")
    public Post updateOnePost(@PathVariable Long postid, @RequestBody PostUpdateRequest newPostUpdateRequest){
        return postService.updateOnePost(postid, newPostUpdateRequest);
    }

    @DeleteMapping("/{postid}")
    public void DeleteOnePost(@PathVariable Long postid){
        postService.deleteOnePost(postid);
    }
}
