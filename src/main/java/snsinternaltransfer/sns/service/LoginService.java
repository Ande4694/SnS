package snsinternaltransfer.sns.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.User;
import snsinternaltransfer.sns.repo.UserRepo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;

    public String hashPW(String password,String username)throws NoSuchAlgorithmException {
        {
            String passwordToHash = password+userRepo.getUserViaUsername(username).getSalt();
            String generatedPassword = null;
            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            return generatedPassword;
        }
    }

    public boolean loginMatch(User user)throws NoSuchAlgorithmException{
        boolean loginMatch;

        User loginUser = userRepo.findLogin(user.getUsername(),hashPW(user.getPassword(), user.getUsername()));

        if(loginUser.getUsername() != null && loginUser.getPassword() != null) {
            loginMatch = true;
        }
        else{
            loginMatch = false;
        }

        return loginMatch;
    }

    public User loggedIn(User user) {

        user = userRepo.findLogin(user.getUsername(),user.getPassword());
        return user;
    }






}