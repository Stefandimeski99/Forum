package com.example.project.Web;

import com.example.project.Model.User;
import com.example.project.Service.CommentService;
import com.example.project.Service.ReplyService;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final UserService userService;
    private final CommentService commentService;
    private final ReplyService replyService;

    public CommentController(UserService userService, CommentService commentService, ReplyService replyService) {
        this.userService = userService;
        this.commentService = commentService;
        this.replyService = replyService;
    }

    @PostMapping("/delete/{commentId}/{parentId}")
    public String removeComment(@PathVariable Long commentId,
                                @PathVariable Long parentId){
        this.commentService.removeCommentById(commentId);
        return "redirect:/post/{parentId}";
    }

    @PostMapping("/{parentId}/{commentId}/addReply")
    public String addReply(@PathVariable Long parentId,
                           @PathVariable Long commentId,
                           @RequestParam String replyText,
                           HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        replyService.addReply(replyText, commentId, user);
        return "redirect:/post/{parentId}";
    }

    @PostMapping("/{parentId}/{replyId}/removeReply")
    public String removeReply(@PathVariable Long parentId,
                              @PathVariable Long replyId){
        this.replyService.removeReply(replyId);
        return "redirect:/post/{parentId}";
    }
}
