package com.QuestAPP.demo.repositories;

import com.QuestAPP.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long > {


    List<Post> findByUserId(Long user_id); //bunu jpa repo kendi kdluyo post içindeki elemanlaeın hepsini
    //findby ı önüne getirerek bulabilriz mesela findbytext findbytitle vs aslında burada yaptığı şey
    //bütün postlar içinde userid si belirttiğimiz userid olan postları bulmak
}
