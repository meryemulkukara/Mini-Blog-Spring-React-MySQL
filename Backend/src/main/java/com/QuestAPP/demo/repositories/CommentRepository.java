package com.QuestAPP.demo.repositories;

import com.QuestAPP.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long > {
    List<Comment> findByUserId(Long userid);

    List<Comment> findByPostId(Long postid);

    List<Comment> findByUserIdAndPostId(Long userid, Long postid);
}
