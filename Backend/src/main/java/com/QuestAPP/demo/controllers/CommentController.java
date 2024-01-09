package com.QuestAPP.demo.controllers;


import com.QuestAPP.demo.entities.Comment;
import com.QuestAPP.demo.requests.CommentCreateRequest;
import com.QuestAPP.demo.requests.UpdateCommentRequest;
import com.QuestAPP.demo.responses.CommentResponse;
import com.QuestAPP.demo.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    CommentService commentService;

    public CommentController( CommentService commentService){
        this.commentService=commentService;
    }


    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userid, @RequestParam Optional<Long> postid){
        return commentService.getAllComments(userid, postid);
    }




    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @GetMapping("/{commentid}")
    public  Comment getOneComment( @PathVariable Long commentid){
        return commentService.getOneComment(commentid);
    }

    @PutMapping("/{commentid}")
    public Comment updateOneComment( @PathVariable Long commentid,@RequestBody UpdateCommentRequest updateCommentRequest){
        return commentService.updateOneComment(commentid, updateCommentRequest);
    }

    @DeleteMapping("/{commentid}")
    public void deleteOneComment(@PathVariable Long commentid){
        commentService.deleteOneComment( commentid);
    }
}
