package com.example.project.Model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String context;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies;

    private String date;

    private int likes;

    public Comment() {
    }

    public Comment(String context, User user, String date, Article article) {
        this.context = context;
        this.article = article;
        this.user = user;
        this.date = date;
        this.likes = 0;
    }
}
