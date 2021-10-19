package com.example.project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comment;
    @Lob
    private String context;
    private String title;
    private String topic;
    private int likes;
    private String date;
    private int numComments;

    public Article() {
    }

    public Article(String context, User user, String topic, String title, String date) {
        this.context = context;
        this.user = user;
        this.title = title;
        this.topic = topic;
        this.date = date;
        this.likes = 0;
    }
}
