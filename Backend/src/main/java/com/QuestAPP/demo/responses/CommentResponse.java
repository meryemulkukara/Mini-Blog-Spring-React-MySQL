package com.QuestAPP.demo.responses;

import com.QuestAPP.demo.entities.Comment;
import com.QuestAPP.demo.entities.Post;
import lombok.Data;

@Data
public class CommentResponse {

    Long id;

    Long postid;
    Long userid;
    String username;
    String text;

    public CommentResponse( Comment entity){
        this.id= entity.getId();
        this.postid= entity.getPost().getId();
        this.userid=(entity.getUser()).getId();
        this.username= (entity.getUser()).getUsername();
        this.text= entity.getText();
    }
}

