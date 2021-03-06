package az.task.demo.CustomExceptions;

import az.task.demo.CustomExceptions.BaseExceptionClass.BaseNotFoundException;

public class UserNotFound extends BaseNotFoundException {
    private final static String message="USER NOT FOUND, ID =";

    public UserNotFound(int id) {
        super(id,message);
    }



    @Override
    public String toString() {
        return "UserNotFound{" +
                "id=" + id +
                '}';
    }
}
