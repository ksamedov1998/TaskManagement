package az.task.demo.Util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static String encryptPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
}
