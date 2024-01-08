package com.QuestAPP.demo.repositories;

import com.QuestAPP.demo.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long > {
    List<Like> findByUserId(Long userid);

    List<Like> findByPostId(Long postid);

    List<Like> findByUserIdAndPostId(Long userid, Long postid);
}
