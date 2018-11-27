
package snsinternaltransfer.sns.controller;



import java.security.Principal;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.ItemRepo;
import snsinternaltransfer.sns.repo.TransferRepo;



import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

    private final Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    TransferRepo transferRepo;
    ItemRepo itemRepo;

    @GetMapping("/")
    public String index(){

        log.info("index called");



        return "index";

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






    @GetMapping("/userCreate")
    public String userCreate(Model model){

        log.info("userCreate call");

        model.addAttribute("transfer", new Transfer());

        return "userCreate";
    }

//    @PostMapping("/userCreate")
//    public String userCreate(@ModelAttribute Transfer transfer) {
//
//        transferRepo.sendItem(transfer);
//
//
//
//        log.info("bla bla:"+transfer.getItem());
//
//        return "redirect:/userCreate";
//    }

    @RequestMapping(value = "/userCreate")
    public void sendItem (@RequestParam("to")String to,
                                  @RequestParam("from")String from,
                                  @RequestParam("date") String date,
                                  @RequestParam("item")String item,
                                  @RequestParam("sender")String sender,
                                  @RequestParam("amount")double amount) throws Exception {






        log.info("from:" + from);
        log.info("to:" + to);
        log.info("date:" + date);
        log.info("item:" + item);
        log.info("sender:" + sender);
        log.info("amount:" + amount);





        transferRepo.sendItem(to, from, date, item, sender, amount);

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


