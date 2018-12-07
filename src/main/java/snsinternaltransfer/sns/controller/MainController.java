
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
import snsinternaltransfer.sns.utility.ExcelUtils;
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

        if (a.exists()) {

            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                    // Content-Type
                    .contentType(mediaType) //
                    // Content-Lengh
                    .contentLength(data.length) //
                    .body(resource);
        }


    }

    @RequestMapping("/download1")
    public ResponseEntity<InputStreamResource> downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    @GetMapping("/download3")
    public void downloadFile3(HttpServletResponse resonse,
                              @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        File file = new File(DIRECTORY + "/" + fileName);

        // Content-Type
        // application/pdf
        resonse.setContentType(mediaType.getType());

        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

        // Content-Length
        resonse.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }

    @GetMapping("/")
    public String index() {

        log.info("index called");


        return "index";

    }

    @GetMapping("/login")
    public String loginPage(Model model) {

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

        return "userCreate";
    }

    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute Transfer transfer) {

        transferService.sendItem(transfer);



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



        ///// DELETE FILE?????









        model.addAttribute("date", new Item());

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
        model.addAttribute("date", new Item());
        model.addAttribute("sendings", transferService.getAllTransfers());


        tempId = idForUpdate;

        if (transferService.selectTranfer(idForUpdate) != null) {

            return "updateSending";

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
        model.addAttribute("date", new Item());

        //mangler search med javascript funtion i html DONE

        log.info("itemList call");
        return "itemList";
    }

    @GetMapping("/editItem")
    public String editItem(Model model) {
        model.addAttribute("date", new Item());

        log.info("editItem call");
        return "editItem";
    }

    @GetMapping("/searchItem")
    public String searchItem(Model model) {
        model.addAttribute("date", new Item());

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
    public String excel(@ModelAttribute String s)  {

        log.info("someone is writing to excel with all info from after: ");

        excelRepo.writeAllToExcel(s);

        log.info("dir : " + System.getProperty("java.io.tmpdir"));


        File curDir = new File(System.getProperty("java.io.tmpdir"));


            File[] filesList = curDir.listFiles();
            for(File f : filesList){

                if(f.isFile()){


                    log.info("ba: "+f.getName());
                    log.info("ba: "+f.getAbsolutePath());
                }
            }



        log.info("asd"+ System.getProperty("java.io."));
            File a = new File(DIRECTORY + "/" + DEFAULT_FILE_NAME);
            if (a.exists()){
                return "redirect:download";
            } else return "redirect:adminMenu";

        //return "redirect:download1";
        //return "redirect:download3";
    }



}
