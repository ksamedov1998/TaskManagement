package az.task.demo.Service;

import az.task.demo.Domains.Log.Log;

import java.util.logging.LogRecord;

public interface LogService {
    void save(Log log);
}
