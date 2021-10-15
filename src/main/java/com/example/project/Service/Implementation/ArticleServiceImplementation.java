package com.example.project.Service.Implementation;

import com.example.project.Model.Article;
import com.example.project.Model.User;
import com.example.project.Repository.ArticleRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.Service.ArticleService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleServiceImplementation implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImplementation(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Article> findAllPosts() {
        return this.articleRepository.findAll();
    }

    @Override
    public List<Article> findByTitle(String title) {
        return this.articleRepository.findAllByTitleLike(title);
    }

    @Override
    public Article addPost(String postContext, User user, String topic, String postTitle) {
        Article article = new Article(postContext, user, topic, postTitle,  LocalDate.now().toString());
        return this.articleRepository.save(article);
    }

    @Override
    public Article findById(Long id) {
        return this.articleRepository.findById(id).get();
    }

    @Override
    public List<Article> listArticlesByTopic(String topic) {
        return this.articleRepository.findAllByTopic(topic);
    }

    @Override
    public void deleteById(Long id) {
        this.articleRepository.deleteById(id);
    }

    @Override
    public Article editPost(String title, String topic, String context, Long id) {
        Article article = this.articleRepository.findById(id).get();
        article.setTitle(title);
        article.setTopic(topic);
        article.setContext(context);
        return this.articleRepository.save(article);
    }
}
