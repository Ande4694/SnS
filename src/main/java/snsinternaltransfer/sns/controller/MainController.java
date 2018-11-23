
package snsinternaltransfer.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.User;
import snsinternaltransfer.sns.repo.UserRepo;
import snsinternaltransfer.sns.service.LoginService;

import java.util.ArrayList;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {


    @Autowired
    LoginService loginService;
    ArrayList<User> loggedInUsers;

    private final Logger log = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String index(Model model){

        log.info("index called");

        model.addAttribute("user",new User());

        return "index";

    }

    @PostMapping("/")
    public String index(@ModelAttribute User user){

        log.info("someone logged in as: "+user.getUsername());


        if(loginService.login(user.getUsername(),user.getPassword())!=null){
            loggedInUsers.add(loginService.login(user.getUsername(),user.getPassword()));

            return "index";

        } else {
            return "loginFail";
        }









    }


    @GetMapping("/userCreate")
    public String userCreate(){

        log.info("userCreate call");
        return "userCreate";
    }

    @GetMapping("/adminMenu")
    public String adminMenu(){

        log.info("adminMenu call");
        return "adminMenu";
    }

    @GetMapping("/itemList")
    public String itemList(){

        log.info("itemList call");
        return "itemList";
    }
}


