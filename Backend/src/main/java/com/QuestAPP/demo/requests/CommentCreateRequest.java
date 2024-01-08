package com.QuestAPP.demo.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id;
    Long postid;
    Long userid;
    String text;
}
