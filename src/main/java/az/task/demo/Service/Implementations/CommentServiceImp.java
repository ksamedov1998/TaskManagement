package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.CommentNotFound;
import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.TaskNotFound;
import az.task.demo.Domains.Comment;
import az.task.demo.Domains.Enums.Status;
import az.task.demo.Repository.CommentRepository;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Service.CommentService;
import az.task.demo.Util.DynamicQueryUtil;
import az.task.demo.Util.LogHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Level;

import static az.task.demo.Util.Numbers.*;

@Service
@Transactional
public class CommentServiceImp implements CommentService {

    private final LogHandler logHandler;
    private final CommentRepository commentRepository;
    private final HibernateRepository hibernateRepository;

    public CommentServiceImp(LogHandler logHandler, CommentRepository commentRepository, HibernateRepository hibernateRepository) {
        this.logHandler = logHandler;
        this.commentRepository = commentRepository;
        this.hibernateRepository = hibernateRepository;
    }

    @Override
    public Comment addComment(int taskId, Comment comment) {
            comment.setStatus(Status.ACTIVE.getValue());
        Optional<Comment> returnedComment=commentRepository.save(taskId,comment);
            if(!returnedComment.isPresent()){
                logHandler.publish(new LogBuilder()
                        .setPoint("CommontServiceImp.addComment")
                        .setException("TaskNotFoundException")
                        .setLevel(Level.WARNING.getName())
                        .setState("FAIL")
                        .build()
                );
                    throw new TaskNotFound(taskId);
             }
            return returnedComment.get();
    }

    @Override
    public void deleteComment(int commentId) {
        if(commentRepository.deleteById(commentId)==NOT_SUCCESSFUL){
            logHandler.publish(new LogBuilder()
                    .setPoint("CommontServiceImp.deleteComment")
                    .setException("CommentNotFoundException")
                    .setLevel(Level.WARNING.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new CommentNotFound(commentId);
        }

    }

    @Override
    public Comment updateComment(int commentId, Comment comment) {
        Optional<Comment> optionalComment=hibernateRepository.update(Comment.class,commentId,DynamicQueryUtil.createQuery(commentId,comment));
        if(optionalComment.isEmpty()){
            logHandler.publish(new LogBuilder()
                    .setPoint("CommontServiceImp.updateComment")
                    .setException("CommentNotFoundException")
                    .setLevel(Level.WARNING.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new CommentNotFound(commentId);
        }
        return optionalComment.get();
    }
}
