package snsinternaltransfer.sns.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {

    // Encryte Password with BCryptPasswordEncoder
    public static String encryptedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "Admin";
        String encryptedPassword = encryptedPassword(password);

        System.out.println("Encryted Password: " + encryptedPassword);
    }

}