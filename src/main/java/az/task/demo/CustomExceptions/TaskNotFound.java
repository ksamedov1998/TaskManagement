package az.task.demo.CustomExceptions;

import az.task.demo.CustomExceptions.BaseExceptionClass.BaseNotFoundException;

public class TaskNotFound extends BaseNotFoundException {

    private final static String message="TASK NOT FOUND , ID =";

    public TaskNotFound(int id) {
        super(id,message);
    }

    @Override
    public String toString() {
        return "TaskNotFound{" +
                "id=" + id +
                '}';
    }
}
