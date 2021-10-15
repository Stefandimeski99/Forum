package com.example.project.Web;

import com.example.project.Model.Article;
import com.example.project.Model.User;
import com.example.project.Service.ArticleService;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final ArticleService articleService;

    public HomeController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping
    public String homePage(Model model,
                           @RequestParam(required = false) String search,
                           HttpServletRequest servletRequest){
        List<Article> posts;
        User user = (User) servletRequest.getSession().getAttribute("user");
        if(search == null) {
            posts = this.articleService.findAllPosts();
        }else{
            posts = this.articleService.listArticlesByTopic(search);
        }
        if(posts.isEmpty()){
            model.addAttribute("noPosts", search);
        }else{
            model.addAttribute("noPosts", null);
        }
        model.addAttribute("posts", posts);
        if(user != null) {
            model.addAttribute("username", user.getUsername());
        }else{
            model.addAttribute("username", null);
        }
        String posted = (String) servletRequest.getSession().getAttribute("posted");
        if(posted != null){
            model.addAttribute("posted", "true");
            servletRequest.getSession().removeAttribute("posted");
            Long id = (Long) servletRequest.getSession().getAttribute("articleId");
            model.addAttribute("articleId", id);
            servletRequest.getSession().removeAttribute("articleId");
        }
        return "home.html";
    }

    @PostMapping("/submit")
    public String submitPost(@RequestParam String postTitle,
                             @RequestParam String topic,
                             @RequestParam String postContext,
                             HttpServletRequest servletRequest){
        User user = (User) servletRequest.getSession().getAttribute("user");
        servletRequest.getSession().setAttribute("posted", "true");
        Article article = this.articleService.addPost(postContext, user, topic, postTitle);
        servletRequest.getSession().setAttribute("articleId", article.getId());
        return "redirect:/home";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id){
        this.articleService.deleteById(id);
        return "redirect:/home";
    }
}
