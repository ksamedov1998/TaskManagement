package az.task.demo.CustomExceptions;

import az.task.demo.CustomExceptions.BaseExceptionClass.BaseNotFoundException;

public class CommentNotFound extends BaseNotFoundException {
    private final static String message="COMMENT NOT FOUND , ID =";

    public CommentNotFound(int id) {
        super(id, message);
    }
}
