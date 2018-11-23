
package snsinternaltransfer.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

    private final Logger log = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String index(){

        log.info("index called");



        return "index";

    }

    @GetMapping("/login")
    public String login (){
        log.info("login attempted");
        return "/login";
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


