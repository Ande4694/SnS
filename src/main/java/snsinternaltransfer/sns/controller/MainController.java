
package snsinternaltransfer.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.User;
import snsinternaltransfer.sns.repo.UserRepo;
import snsinternaltransfer.sns.service.LoginService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {


    @Autowired
    LoginService loginService;

    User loggedIn = new User();

    private final Logger log = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String index(){

        log.info("index called");



        return "index";

    }

    @GetMapping("/login")
    public String login (Model model){
        log.info("login attempted");

        model.addAttribute("user",new User());
        model.addAttribute("isLogin", true);

        if(loggedIn.getUserState() == 1) {
            model.addAttribute("isLoggedin", true);
            model.addAttribute("isAdmin", true);
            model.addAttribute("userName", loggedIn.getUsername());
        } else if (loggedIn.getUserState() == 0){
            model.addAttribute("isLoggedin", true);
            model.addAttribute("userName", loggedIn.getUsername());
        }
        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) throws NoSuchAlgorithmException {
        boolean loginMatch = false;
        loginMatch = loginService.loginMatch(user);

        if(loginMatch == true) {


            loggedIn = loginService.loggedIn(user);

            return "redirect:/";
        }
        else {


            return "redirect:/login";
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


