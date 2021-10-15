package com.example.project.Service;

import com.example.project.Model.Comment;
import com.example.project.Model.Reply;
import com.example.project.Model.User;

import java.util.List;

public interface ReplyService {
    public Reply addReply(String context, Long commentId, User user);
    public Reply removeReply(Long id);
    public List<Reply> getAllReplies();
}
