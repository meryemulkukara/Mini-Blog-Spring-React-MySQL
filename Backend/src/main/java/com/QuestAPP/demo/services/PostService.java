package com.QuestAPP.demo.services;

import com.QuestAPP.demo.entities.Like;
import com.QuestAPP.demo.entities.Post;
import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.repositories.PostRepository;
import com.QuestAPP.demo.requests.PostCreateRequest;
import com.QuestAPP.demo.requests.PostUpdateRequest;
import com.QuestAPP.demo.responses.LikeResponse;
import com.QuestAPP.demo.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    private LikeService likeService;


    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService= userService;

    }

    @Autowired
    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
    }


    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user= userService.getOneUserById(newPostCreateRequest.getUserid());
        if(user==null){ return null;}
        else{
            Post newPost=new Post();
            newPost.setId(newPostCreateRequest.getId());
            newPost.setTitle(newPostCreateRequest.getTitle());
            newPost.setText(newPostCreateRequest.getText());
            newPost.setUser(user);
            return postRepository.save(newPost);

        }
    }

    public Post getOnePostById(Long post_id) {
        return postRepository.findById(post_id).orElse(null);
    }

  //  public Post updateOnePost(Long post_id, Post newPost) {
  //      Optional<Post> post= postRepository.findById(post_id);
  //      if(post.isPresent()){
  //          Post foundPost= post.get();
  //          foundPost.setTitle(newPost.getTitle());
  //          foundPost.setText(newPost.getText());
  //          postRepository.save(foundPost);
  //          return foundPost;
  //      }
  //      else{ return null;}



    public void deleteOnePost(Long post_id) {
        postRepository.deleteById(post_id);
    }


    public List<PostResponse> getAllPosts(Optional<Long> user_id) {
        List<Post> list;
        if(user_id.isPresent()){
            list= postRepository.findByUserId(user_id.get());

        }
        else{        list= postRepository.findAll(); }
        return list.stream().map(p -> {
            List<LikeResponse> likes=likeService.getAllLikes(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes); }).collect(Collectors.toList());
    }

    public Post updateOnePost(Long post_id, PostUpdateRequest newPostUpdateRequest) {
        Optional<Post> post= postRepository.findById(post_id);
        if(post.isPresent()){
            Post founded=post.get();
            founded.setTitle(newPostUpdateRequest.getTitle());
            founded.setText(newPostUpdateRequest.getText());
            postRepository.save(founded);
            return founded;
        }
        else{
            return null;
        }
    }
}
