
package snsinternaltransfer.sns.controller;



import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.exelRepo.ExcelRepo;
import snsinternaltransfer.sns.repo.login.AppUserDAO;
import snsinternaltransfer.sns.service.ItemService;
import snsinternaltransfer.sns.service.TransferService;
import snsinternaltransfer.sns.utility.MediaTypeUtils;
import snsinternaltransfer.sns.utility.WebUtils;

import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class MainController {

    private final Logger log = Logger.getLogger(MainController.class.getName());
    private int tempId;
    private static final String DIRECTORY = System.getProperty("java.io.tmpdir");
    private static final String DEFAULT_FILE_NAME = "TransferSheet.xlsx";
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @Autowired
    AppUserDAO appUserDAO;
    @Autowired
    TransferService transferService;
    @Autowired
    ItemService itemService;
    @Autowired
    ExcelRepo excelRepo;
    @Autowired
    ServletContext servletContext;


    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        Path path = Paths.get(DIRECTORY + "/" + DEFAULT_FILE_NAME);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        File a = new File(DIRECTORY + "/" + DEFAULT_FILE_NAME);


        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);

    }


    @GetMapping("/")
    public String index() {

        log.info("index called");


        return "index";

    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping("/403")
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

        //model.addAttribute("searchKey", itemService.searchItem("tuna"));

        return "userCreate";
    }



    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute Transfer transfer) {

        transferService.sendItem(transfer);



        // brugt til fejlfinding
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

        // tilføjer alle transfers til udskrift
        model.addAttribute("sendings", transferService.getAllTransfers());

        // item er en tom string til at postmappe til at kalde excel-genereringen
        model.addAttribute("date", new Item());

        log.info("adminMenu call");
        return "adminMenu";
    }

    @GetMapping("/deleteSending/{deleted}")
    public String deleteSending(@PathVariable("deleted") int idForDelete) {

        log.info("Thomas has tried to delete: " + idForDelete);


        //test om den pågældende sending findes, før man sletter
        if (transferService.selectTranfer(idForDelete) != null) {

            transferService.deleteSending(idForDelete);

        }


        return "redirect:/adminMenu";
    }

    @GetMapping("/updateSending/{updated}")
    public String updateSending(@PathVariable("updated") int idForUpdate, Model model) {

        log.info("Thomas has tried to update: " + idForUpdate);

        // tom transffer der settes senere ved post
        model.addAttribute("update", new Transfer());

        // tom string til excel funktion
        model.addAttribute("date", new Item());

        model.addAttribute("sendings", transferService.getAllTransfers());


        // bruges til holde det unikke id
        tempId = idForUpdate;


        // tester om det pågældende transfer findes, før man updater
        if (transferService.selectTranfer(idForUpdate) != null) {

            return "updateSending";

        }


        return "redirect:/adminMenu";
    }

    @PostMapping("/updateSending")
    public String updateSending(@ModelAttribute Transfer transfer) {


        log.info("edit item was done on item: " + transfer.getItem());


        transferService.updateTransfer(transfer, tempId);



        return "redirect:/adminMenu";
    }

    @GetMapping("/itemList")
    public String itemList(Model model) {

        // henter alle items
        model.addAttribute("items", itemService.getAllItems());

        // til excel funktion
        model.addAttribute("date", new Item());


        log.info("itemList call");
        return "itemList";
    }

    @GetMapping("/editItem")
    public String editItem(Model model) {

        // til excel funktion
        model.addAttribute("date", new Item());

        log.info("editItem call");
        return "editItem";
    }


    @GetMapping("/deleteItem/{deleted}")
    public String deleteItem(@PathVariable("deleted") int idForDelete) {


        log.info("Thomas has tried to delete: " + idForDelete);


        // tester om itemmet med det unikke id findes, før man kalder slet funktion
        if (itemService.selectItem(idForDelete) != null) {

            itemService.deleteItem(idForDelete);

        }


        return "redirect:/itemList";
    }

    @GetMapping("/updateItem/{updated}")
    public String updateItem(@PathVariable("updated") int idForUpdate, Model model) {

        log.info("Thomas has tried to update: " + idForUpdate);

        model.addAttribute("update", new Item());
        model.addAttribute("date", new Item());
        model.addAttribute("items", itemService.getAllItems());


        tempId = idForUpdate;

        if (itemService.selectItem(idForUpdate) != null) {

            return "updateItem";

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
        model.addAttribute("date", new Item());

        log.info("someone is trying to create an item");

        // binder en tom "item" til keyen "newItem"
        model.addAttribute("newItem", new Item());


        return "createItem";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item) {

        // setter det tomme item
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
    public String excel(@ModelAttribute String s)  {

        log.info("someone is writing to excel with all info from after: ");

        // skriver til excel
        excelRepo.writeAllToExcel(s);

        // brugt til at finde hvor excel gemmes
        log.info("dir : " + System.getProperty("java.io.tmpdir"));


        // returnere alt der findes på stien hvor excel burde gemmes
        File curDir = new File(System.getProperty("java.io.tmpdir"));


            File[] filesList = curDir.listFiles();
            for(File f : filesList){

                if(f.isFile()){


                    log.info("ba: "+f.getName());
                    log.info("ba: "+f.getAbsolutePath());
                }
            }



        log.info("asd"+ System.getProperty("java.io."));

            // tester om filen findes, før download funktionen kaldes
            File a = new File(DIRECTORY + "/" + DEFAULT_FILE_NAME);
            if (a.exists()){
                return "redirect:download";
            } else return "redirect:adminMenu";

    }



}
