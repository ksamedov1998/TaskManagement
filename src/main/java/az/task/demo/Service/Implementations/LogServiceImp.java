package az.task.demo.Service.Implementations;

import az.task.demo.Domains.Log;
import az.task.demo.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImp implements az.task.demo.Service.LogService {

    @Autowired
    private LogRepository logRepository;

    public void save(Log log) {
        logRepository.save(log);
    }
}
