package az.task.demo.Util;

import az.task.demo.Domains.Log;

import java.util.logging.Level;

public class LogCreator {

    public static Log createLog(){
        return new Log();
    }

    public static Log createLog(Level level){
        Log log= new Log();
        log.setLevel(level.getName());
        return log;
    }

    public static Log createLog(String state,Level level, String exception, String desc, String point){
        Log log= new Log();
        log.setLevel(level.getName());
        log.setState(state);
        log.setException(exception);
        log.setPoint(point);
        log.setDescription(desc);
        log.setState(state);
        return log;
    }

    public static Log createLog(String state,Level level, String exception){
        Log log= new Log();
        log.setLevel(level.getName());
        log.setException(exception);
        log.setState(state);
        return log;
    }

    public static Log createLog(String state,Level level, String exception,  String point){
        Log log= new Log();
        log.setLevel(level.getName());
        log.setException(exception);
        log.setPoint(point);
        return log;
    }

}
