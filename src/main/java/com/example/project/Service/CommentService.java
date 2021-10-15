package com.example.project.Service;

import com.example.project.Model.Article;
import com.example.project.Model.Comment;
import com.example.project.Model.User;

public interface CommentService {
    public Comment addComment(String context, User user, Article article);
    public Comment removeCommentById(Long id);
}
