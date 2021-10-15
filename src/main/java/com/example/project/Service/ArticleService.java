package com.example.project.Service;

import com.example.project.Model.Article;
import com.example.project.Model.User;

import java.util.List;

public interface ArticleService {
    public List<Article> findAllPosts();
    public List<Article> findByTitle(String title);
    public Article addPost(String postContext, User user, String topic, String postTitle);
    public Article findById(Long id);
    public List<Article> listArticlesByTopic(String topic);
    public void deleteById(Long id);
    public Article editPost(String title, String topic, String context, Long id);
}
