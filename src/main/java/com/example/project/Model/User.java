package com.example.project.Model;

import com.example.project.Model.Enum.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Article> articleList;

    @OneToMany(mappedBy = "user")
    private List<Reply> replies;

    @Id
    @GeneratedValue
    private Long id;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
}
