package az.task.demo.Configuration;


import az.task.demo.Security.TokenFilter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Order(1)
public class Firebase {
    @Bean
    public FirebaseApp firebaseInit() throws IOException {
        FileInputStream refreshToken = new FileInputStream("/home/kamran/Desktop/taskmanagement-41a46-firebase-adminsdk-nd2wg-2731e63c32.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://taskmanagement-41a46.firebaseio.com/")
                .setProjectId("taskmanagement-41a46 ")
                .build();
        return FirebaseApp.initializeApp(options,"smth");
    }

}
