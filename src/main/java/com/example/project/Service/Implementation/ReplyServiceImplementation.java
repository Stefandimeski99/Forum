package com.example.project.Service.Implementation;

import com.example.project.Model.Comment;
import com.example.project.Model.Reply;
import com.example.project.Model.User;
import com.example.project.Repository.CommentRepository;
import com.example.project.Repository.ReplyRepository;
import com.example.project.Service.ReplyService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
public class ReplyServiceImplementation implements ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public ReplyServiceImplementation(ReplyRepository replyRepository, CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Reply addReply(String context, Long commentId, User user) {
        Comment comment = this.commentRepository.findById(commentId).get();
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        Reply reply = new Reply(context, comment, formattedDate, user);
        comment.getReplies().add(reply);
        return this.replyRepository.save(reply);
    }

    @Override
    public Reply removeReply(Long id) {
        Reply reply = this.replyRepository.findById(id).get();
        this.replyRepository.delete(reply);
        return reply;
    }

    @Override
    public List<Reply> getAllReplies() {
        return this.replyRepository.findAll();
    }
}
