package az.task.demo.Configuration;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Configuration
@Order(1)
public class Firebase {
    private Object DateTimeFormatter;

    //    @Bean
    public FirebaseApp firebaseInit() throws IOException {
        FileInputStream refreshToken = new FileInputStream("/home/kamran/Downloads/taskmanagement-41a46-firebase-adminsdk-nd2wg-e35f6d5b1d.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://taskmanagement-41a46.firebaseio.com/")
                .setProjectId("taskmanagement-41a46")
                .build();
        return FirebaseApp.initializeApp(options);
    }

}
