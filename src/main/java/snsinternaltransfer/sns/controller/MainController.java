
package snsinternaltransfer.sns.controller;



import java.security.Principal;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.login.AppUserDAO;
import snsinternaltransfer.sns.service.ItemService;
import snsinternaltransfer.sns.service.TransferService;
import snsinternaltransfer.sns.utility.WebUtils;

import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

    private final Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    AppUserDAO appUserDAO;
    @Autowired
    TransferService transferService;
    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String index(){

        log.info("index called");



        return "index";

    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }



    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }


    @GetMapping("/userCreate")
    public String userCreate(Model model){

        log.info("userCreate call");

        model.addAttribute("transfer", new Transfer());

        return "userCreate";
    }

    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute Transfer transfer) {

        transferService.sendItem(transfer);

        //HTML MANGLER DOUBLE PÅ amount
        // HTML MANGLER DROP DOWN PÅ TO OG FROM

        /*
        brug disse navne:
        Nansensgade
        Hellerup
        Østerbro
        Istedgade
        Gammel Kongevej
        Valby
        Lyngby
        Tivoli Hotel
        Rungsted
        Borgergade
        Krudthuset
        Tivoli Garden
        Baghuset
         */

        log.info("from:" + transfer.getFrom());
        log.info("to:" + transfer.getTo());
        log.info("date:" + transfer.getSendingDate());
        log.info("item:" + transfer.getItem());
        log.info("sender:" + transfer.getSenderName());
        log.info("amount:" + transfer.getAmount());

        return "redirect:/userCreate";
    }

    @GetMapping("/adminMenu")
    public String adminMenu(){

        log.info("adminMenu call");
        return "adminMenu";
    }

    @GetMapping("/itemList")
    public String itemList(Model model){

        model.addAttribute("items", itemService.getAllItems());

        //mangler search med javascript funtion i html
        // skal tilføjes til admin only

        log.info("itemList call");
        return "itemList";
    }

    @GetMapping("/createItem")
    public String createItem(Model model){

        log.info("someone is trying to create an item");

        model.addAttribute("newItem", new Item());

        // skal tilføjes til admin only

        return "createItem";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item){

        itemService.createItem(item);

        log.info("someone created a new item: "+item.getName());

        return "redirect:/itemList";
    }
}


