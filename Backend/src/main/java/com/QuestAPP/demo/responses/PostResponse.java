package com.QuestAPP.demo.responses;

import com.QuestAPP.demo.entities.Like;
import com.QuestAPP.demo.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userid;
    String username;
    String text;
    String title;

    List<LikeResponse> postLikes;

    public PostResponse( Post entity, List<LikeResponse> likes){
        this.id= entity.getId();
        this.userid=(entity.getUser()).getId();
        this.username= (entity.getUser()).getUsername();
        this.text= entity.getText();
        this.title= entity.getTitle();
        this.postLikes=likes;
    }
}
