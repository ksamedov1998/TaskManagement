package az.task.demo.Configuration;


import az.task.demo.Util.DateTimeFormattingUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order(1)
public class Config {

    @Bean
    DateTimeFormattingUtil timeFormattingUtil(){
        return new DateTimeFormattingUtil();
    }
}