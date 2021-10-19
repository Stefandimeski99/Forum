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
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis maximus pharetra lobortis. " +
                "In orci mi, suscipit sed cursus sit amet, condimentum eu velit. Praesent sagittis urna nec " +
                "rutrum facilisis. Integer tempus pretium est sed feugiat. Fusce sollicitudin, lacus eu " +
                "dignissim consectetur, sapien augue tincidunt odio, ut malesuada justo neque vitae eros. " +
                "Curabitur egestas leo nibh, at molestie turpis gravida sed. Pellentesque congue nunc at elit " +
                "vulputate, sed placerat dolor elementum. Duis diam ipsum, blandit eu congue a, pellentesque ac " +
                "odio. Proin ac feugiat nulla. Nulla mollis, ante eu tempus mattis, magna odio malesuada ligula, " +
                "finibus mollis orci justo id lectus. Sed scelerisque, lacus a varius sodales, tellus nulla " +
                "iaculis ipsum, a pellentesque felis mauris nec turpis. Aliquam at ligula eget nunc semper imperdiet ut ac " +
                "odio. Quisque consectetur ligula nisi, at pretium urna porta at.";
        Article article = new Article(lorem, user, "Python", "I have this problem",  LocalDate.now().toString());
        this.articleRepository.save(article);
    }

}
