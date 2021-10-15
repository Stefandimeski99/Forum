package com.example.project.Service.Implementation;

import com.example.project.Model.Article;
import com.example.project.Model.Comment;
import com.example.project.Model.User;
import com.example.project.Repository.ArticleRepository;
import com.example.project.Repository.CommentRepository;
import com.example.project.Service.CommentService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentServiceImplementation(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public Comment addComment(String context, User user, Article article) {
        Comment comment = new Comment(context, user, LocalDate.now().toString(), article);
        article.getComment().add(comment);
        article.setNumComments(article.getNumComments() + 1);
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment removeCommentById(Long id) {
        Comment comment = this.commentRepository.findById(id).get();
        this.commentRepository.delete(comment);
        Article article = comment.getArticle();
        article.setNumComments(article.getNumComments() - 1);
        article.getComment().remove(comment);
        return comment;
    }
}
