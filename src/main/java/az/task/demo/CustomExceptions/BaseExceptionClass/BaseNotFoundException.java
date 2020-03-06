package az.task.demo.CustomExceptions.BaseExceptionClass;

public class BaseNotFoundException extends RuntimeException {
    protected int id;
    protected String message;

        public BaseNotFoundException(int id,String message){
            this.id=id;
            this.message=message;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
