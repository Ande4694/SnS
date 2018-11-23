package snsinternaltransfer.sns.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.User;
import snsinternaltransfer.sns.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;
    BCryptPasswordEncoder encoder;


    public User login(String username, String password){
        String hashedAndSaltedPassword = encoder.encode(password+userRepo.getSaltViaUsername(username));
        if (hashedAndSaltedPassword.equals(userRepo.getPasswordViaUsername(username))){

            User user = userRepo.getUserViaUsername(username);

            return user;
        }

        else return null;
    }



    // den her er til for at vi kan lave og teste passwords
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    }
}