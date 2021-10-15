package com.example.project.Web;

import com.example.project.Model.Article;
import com.example.project.Model.Comment;
import com.example.project.Model.Reply;
import com.example.project.Model.User;
import com.example.project.Service.ArticleService;
import com.example.project.Service.CommentService;
import com.example.project.Service.ReplyService;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;
    private final ReplyService replyService;

    public PostController(ArticleService articleService, CommentService commentService, UserService userService, ReplyService replyService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @GetMapping("/{id}")
    public String openPost(@PathVariable Long id,
                           Model model){
        Article article = this.articleService.findById(id);
        model.addAttribute("post", article);
        List<Comment> commentList = this.articleService.findById(id).getComment();
        List<Reply> replies = this.replyService.getAllReplies();
        if(replies != null){
            model.addAttribute("replies", replies);
        }
//        for(int i = 0; i < commentList.size(); i++){
//            model.addAttribute("comment" + i, this.)
//        }
        if(commentList != null){
            model.addAttribute("comments", commentList);
        }
        return "article.html";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable Long id,
                           Model model){
        Article article = this.articleService.findById(id);
        model.addAttribute("post", article);
        return "editPost.html";
    }

    @PostMapping("/{id}/edit")
    public String changePost(@PathVariable Long id,
                             @RequestParam String postTitle,
                             @RequestParam String topic,
                             @RequestParam String postContext){
        this.articleService.editPost(postTitle, topic, postContext, id);
        return "redirect:/post/{id}";
    }

    @PostMapping("/{id}/show")
    public String addComment(@PathVariable Long id,
                             @RequestParam String postContext,
                             HttpServletRequest servletRequest){
        User user = (User) servletRequest.getSession().getAttribute("user");
        Article article = this.articleService.findById(id);
        this.commentService.addComment(postContext, user, article);
        return "redirect:/post/{id}";
    }
}
