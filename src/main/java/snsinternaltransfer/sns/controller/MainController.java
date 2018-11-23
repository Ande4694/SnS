
package snsinternaltransfer.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;


import java.sql.SQLException;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

@Autowired
    TransferRepo tr;


    private final Logger log = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String index(){

        log.info("index called");
        return "index";

    }

    @GetMapping("/userCreate")
    public String userCreate(Model model){

        log.info("userCreate call");

        model.addAttribute("transfer",new String());

        return "userCreate";

    }

    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute String transfer)throws SQLException {

        log.info("bla bla:"+transfer);
        return "redirect:/userCreate";
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

