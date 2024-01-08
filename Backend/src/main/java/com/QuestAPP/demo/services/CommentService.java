package com.QuestAPP.demo.services;

import com.QuestAPP.demo.entities.Comment;
import com.QuestAPP.demo.repositories.CommentRepository;
import com.QuestAPP.demo.requests.CommentCreateRequest;
import com.QuestAPP.demo.requests.UpdateCommentRequest;
import com.QuestAPP.demo.responses.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    CommentRepository commentRepository;
    UserService userService;
    PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService){
        this.commentRepository=commentRepository;
        this.userService=userService;
        this.postService=postService;
    }

    public List<CommentResponse> getAllComments(Optional<Long> userid, Optional<Long> postid ) {
        List <Comment> comment;
        if(userid.isPresent()&& postid.isPresent()){
               comment=  commentRepository.findByUserIdAndPostId(userid.get(),postid.get());
            }
        else if( userid.isPresent()){ comment= commentRepository.findByUserId(userid.get());}
        else if(postid.isPresent()) { comment= commentRepository.findByPostId(postid.get());}
        else{            comment= commentRepository.findAll();        }
        return comment.stream().map( c-> new CommentResponse(c)).collect(Collectors.toList());
    }

  // public List<Comment> getAllCommentsByPost(Optional<Long> postid) {
  //     if(postid.isPresent()){
  //         return commentRepository.findByPostId(postid.get());
  //     }
  //     else { return commentRepository.findAll();
  //     }
  // }


    public Comment createOneComment(CommentCreateRequest newcomment) {
        Comment comment=new Comment();
        if(userService.getOneUserById(newcomment.getUserid())==null || postService.getOnePostById(newcomment.getPostid())==null){
            return null;
        }
        else{
            comment.setId(newcomment.getId());
            comment.setUser(userService.getOneUserById(newcomment.getUserid()));
            comment.setPost(postService.getOnePostById(newcomment.getPostid()));
            comment.setText(newcomment.getText());
            commentRepository.save(comment);
            return comment;
        }

    }

    public Comment getOneComment(Long commentid) {
        return commentRepository.findById(commentid).orElse(null);
    }


    public Comment updateOneComment(Long commentid,UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> comment=commentRepository.findById(commentid);
        if(comment.isPresent()){
            Comment found= comment.get();
            found.setId(updateCommentRequest.getId());
            found.setText(updateCommentRequest.getText());
            commentRepository.save(found);
            return found;
        }
        else { return null;}
    }


    public void deleteOneComment(Long commentid) {
        commentRepository.deleteById(commentid);
    }
}
