package az.task.demo.Service;

import az.task.demo.Domains.Comment;

public interface CommentService {
    Comment addComment(int taskId, Comment comment);
    void deleteComment(int commentId);
    Comment updateComment(int commentId,Comment comment);
}
