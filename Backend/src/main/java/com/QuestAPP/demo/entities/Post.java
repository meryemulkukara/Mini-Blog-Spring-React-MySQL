package com.QuestAPP.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Database e oto id
    // yürütecek blyece biz post create ederken frontend de id eklemeyeceğğiz
    Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;


}
