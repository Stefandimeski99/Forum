package com.example.project.Config;

import com.example.project.Model.Article;
import com.example.project.Model.Enum.Role;
import com.example.project.Model.User;
import com.example.project.Repository.ArticleRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public DataInitializer(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void initData(){
        User user = new User("admin", "admin", Role.ADMIN);
        this.userRepository.save(user);
        Article article = new Article("Sefo kontekst", user, "Python", "Sefo title",  LocalDate.now().toString());
        this.articleRepository.save(article);
    }

}
