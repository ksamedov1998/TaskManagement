package az.task.demo.Service;

import az.task.demo.Domains.Comment;

public interface CommentService {
    void addComment(int taskId, Comment comment);
    void deleteComment(int commentId);
    void updateComment(int commentId,Comment comment);
}
