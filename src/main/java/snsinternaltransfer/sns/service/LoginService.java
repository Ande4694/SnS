package snsinternaltransfer.sns.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.User;
import snsinternaltransfer.sns.repo.UserRepo;

import java.util.ArrayList;


@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;
    
    
    
    public User login (String username, String password){
        if (userRepo.getPasswordViaUsername(username).equals(password)   &&    userRepo.getUserViaUsername(username).getUsername().equals(username)){
            
            User user = userRepo.getUserViaUsername(username);
            
            return user;
            
        } 
        else return null;
    }

}