
package snsinternaltransfer.sns.controller;



import java.io.IOException;
import java.security.Principal;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.excelRepo.ExcelRepo;
import snsinternaltransfer.sns.repo.login.AppUserDAO;
import snsinternaltransfer.sns.service.ItemService;
import snsinternaltransfer.sns.service.TransferService;
import snsinternaltransfer.sns.utility.WebUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

    private final Logger log = Logger.getLogger(MainController.class.getName());
    private int tempId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @Autowired
    AppUserDAO appUserDAO;
    @Autowired
    TransferService transferService;
    @Autowired
    ItemService itemService;
    @Autowired
    ExcelRepo excelRepo;

    @GetMapping("/")
    public String index() {

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
    public String userCreate(Model model) {

        log.info("userCreate call");

        model.addAttribute("transfer", new Transfer());

        return "userCreate";
    }

    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute Transfer transfer) {

        transferService.sendItem(transfer);

        //HTML MANGLER DOUBLE PÅ amount                   DONE
        // HTML MANGLER DROP DOWN PÅ TO OG FROM           Done
        // logout url er /logout                           ok

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
    public String adminMenu(Model model) {

        model.addAttribute("sendings", transferService.getAllTransfers());

        // mangler også en fin lille search java script Done


        log.info("adminMenu call");
        return "adminMenu";
    }

    @GetMapping("/deleteSending/{deleted}")
    public String deleteSending(@PathVariable("deleted") int idForDelete) {

        log.info("Thomas has tried to delete: " + idForDelete);


        if (transferService.selectTranfer(idForDelete) != null) {

            transferService.deleteSending(idForDelete);

        }


        return "redirect:/adminMenu";
    }

    @GetMapping("/updateSending/{updated}")
    public String updateSending(@PathVariable("updated") int idForUpdate, Model model) {

        log.info("Thomas has tried to update: " + idForUpdate);

        model.addAttribute("update", new Transfer());


        tempId = idForUpdate;

        if (transferService.selectTranfer(idForUpdate) != null) {

            return "/updateSending";

        }


        return "redirect:/adminMenu";
    }

    @PostMapping("/updateSending")
    public String updateSending(@ModelAttribute Transfer transfer) {


        log.info("edit item was done on item: " + transfer.getItem());


        transferService.updateTransfer(transfer, tempId);
        // HTML med drop down på to og from


        return "redirect:/adminMenu";
    }

    @GetMapping("/itemList")
    public String itemList(Model model) {

        model.addAttribute("items", itemService.getAllItems());

        //mangler search med javascript funtion i html DONE

        log.info("itemList call");
        return "itemList";
    }

    @GetMapping("/editItem")
    public String editItem(Model model) {

        log.info("editItem call");
        return "editItem";
    }

    @GetMapping("/searchItem")
    public String searchItem(Model model) {

        log.info("searchItem call");
        return "searchItem";
    }

    @GetMapping("/deleteItem/{deleted}")
    public String deleteItem(@PathVariable("deleted") int idForDelete) {

        log.info("Thomas has tried to delete: " + idForDelete);


        if (itemService.selectItem(idForDelete) != null) {

            itemService.deleteItem(idForDelete);

        }


        return "redirect:/itemList";
    }

    @GetMapping("/updateItem/{updated}")
    public String updateItem(@PathVariable("updated") int idForUpdate, Model model) {

        log.info("Thomas has tried to update: " + idForUpdate);

        model.addAttribute("update", new Item());


        tempId = idForUpdate;

        if (itemService.selectItem(idForUpdate) != null) {

            return "/updateItem";

        }


        return "redirect:/itemList";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item) {


        log.info("edit item was done on item: " + item.getName());


        itemService.updateItem(item, tempId);


        return "redirect:/itemList";
    }

    @GetMapping("/createItem")
    public String createItem(Model model) {

        log.info("someone is trying to create an item");

        model.addAttribute("newItem", new Item());


        return "createItem";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item) {

        itemService.createItem(item);


        log.info("someone created a new item: " + item.getName());

        return "redirect:/itemList";
    }


    @GetMapping("/excel")
    public String excel(Model model) {

        log.info("someone called /excel");


        model.addAttribute("date", new Item());


        return "excel";
    }

    @PostMapping("/excel")
    public String excel(@ModelAttribute String s) throws IOException, ClassNotFoundException, SQLException {

        log.info("someone is writing to excel with all info from after: ");

        excelRepo.writeAll(s);

        return "redirect:adminMenu";
    }

}
