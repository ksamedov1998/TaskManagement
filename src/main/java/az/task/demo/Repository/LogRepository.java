package az.task.demo.Repository;

import az.task.demo.Domains.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    Log save(Log log);
}
