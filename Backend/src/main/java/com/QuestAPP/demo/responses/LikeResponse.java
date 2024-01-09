package com.QuestAPP.demo.responses;

import com.QuestAPP.demo.entities.Like;
import lombok.Data;

import javax.swing.text.html.parser.Entity;

@Data
public class LikeResponse {

    Long id;
    Long postid;
    Long userid;

    public LikeResponse( Like entity){
        this.id=entity.getId();
        this.postid= entity.getPost().getId();
        this.userid= entity.getUser().getId();

    }

}
