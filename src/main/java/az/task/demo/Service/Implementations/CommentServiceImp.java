package az.task.demo.Service.Implementations;

import az.task.demo.Domains.Comment;
import az.task.demo.Domains.Task;
import az.task.demo.Repository.CommentRepository;
import az.task.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void addComment(int taskId, Comment comment) {
            // exception task not found
            commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {

    }

    @Override
    public void updateComment(int commentId, Comment comment) {

    }
}
