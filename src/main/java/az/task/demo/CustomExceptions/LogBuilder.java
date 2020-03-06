package az.task.demo.CustomExceptions;

import az.task.demo.Domains.Log;

import java.time.LocalDateTime;

public class LogBuilder {

    private String level;

    private String state;

    private String exception;

    private String description;

    private String point;

    public LogBuilder setLevel(String level) {
        this.level=level;
        return this;
    }


    public LogBuilder setState(String state) {
        this.state=state;
        return this;
    }


    public LogBuilder setException(String exception) {
        this.exception=exception;
        return this;
    }


    public LogBuilder setDescription(String description) {
        this.description=description;;
        return this;
    }


    public LogBuilder setPoint(String point) {
        this.point=point;;
        return this;
    }

    public Log build(){
        Log log=new Log(level,state,exception,description,point, LocalDateTime.now());
        return log;
        }

}
