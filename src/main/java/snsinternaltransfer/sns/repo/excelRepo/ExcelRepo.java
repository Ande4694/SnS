package snsinternaltransfer.sns.repo.excelRepo;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.service.TransferService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ExcelRepo {

    @Autowired
    TransferService transferService;



        String[] columns = {"To", "Date", "Item", "Amount", "Senders Name", "Price", "Item code"};
        List<Transfer> transfersFromNansensgade =  transferService.getTransfersFrom(1);
        List<Transfer> transfersFromHellerup =  transferService.getTransfersFrom(2);
        List<Transfer> transfersFromØsterbro =  transferService.getTransfersFrom(3);
        List<Transfer> transfersFromIstedgade =  transferService.getTransfersFrom(4);
        List<Transfer> transfersFromGlk =  transferService.getTransfersFrom(5);
        List<Transfer> transfersFromValby =  transferService.getTransfersFrom(6);
        List<Transfer> transfersFromLyngby =  transferService.getTransfersFrom(7);
        List<Transfer> transfersFromHotel =  transferService.getTransfersFrom(8);
        List<Transfer> transfersFromRungsted =  transferService.getTransfersFrom(9);
        List<Transfer> transfersFromBorgergade =  transferService.getTransfersFrom(10);
        List<Transfer> transfersFromKrudthuset =  transferService.getTransfersFrom(11);
        List<Transfer> transfersFromGardens =  transferService.getTransfersFrom(12);
        List<Transfer> transfersFromBaghuset =  transferService.getTransfersFrom(13);

        String fileName="C:\\TransferSheet.xlsx";
        Workbook workbook = new XSSFWorkbook();

        /* CreationHelper helps us create instances of various things like DataFormat,
        Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Nansensgade");

        // Create a Row
        Row headerRow = sheet.createRow(0);


}
