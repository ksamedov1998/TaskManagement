package az.task.demo.Util;

import az.task.demo.Domains.Log.Log;
import az.task.demo.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


@Component
public class LogHandler {

    @Autowired
    private LogService logService;

    public void publish(Log logRecord) {
        logService.save(logRecord);
    }

    public void print(Log logRecord){
        System.out.println(logRecord);
    }
}
