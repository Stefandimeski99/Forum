package com.example.project.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reply {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String context;

    private String date;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private User user;

    public Reply() {
    }

    public Reply(String context, Comment comment, String date, User user) {
        this.context = context;
        this.comment = comment;
        this.user = user;
        this.date = date;
    }
}
