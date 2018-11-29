package snsinternaltransfer.sns.repo.excelRepo;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.controller.MainController;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.service.TransferService;

import javax.swing.tree.DefaultTreeCellEditor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ExcelRepo {

    @Autowired
    TransferService transferService;

    private final Logger log = Logger.getLogger(MainController.class.getName());
    @DateTimeFormat(pattern = "yyyy-MM-dd")


    public void write(int dep, LocalDate after)throws SQLException, ClassNotFoundException, IOException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://snsgrp5k.ctjynaaxvgot.eu-central-1.rds.amazonaws.com:3306/sns" ,
                "snsgrp5k" ,
                "snsgrp5k"
        );

        File f = new File("sheets\\TransferSheet"+transferService.getFromViaInt(dep)+".xlsx");
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sns.sendings where `from`= "+dep+" and date >"+ after);
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetname = transferService.getFromViaInt(dep);
        XSSFSheet spreadsheet = workbook.createSheet(sheetname);

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("TO");
        cell = row.createCell(2);
        cell.setCellValue("DATE");
        cell = row.createCell(3);
        cell.setCellValue("ITEM");
        cell = row.createCell(4);
        cell.setCellValue("AMOUNT");
        cell = row.createCell(5);
        cell.setCellValue("SENDERS NAME");
        cell = row.createCell(6);
        cell.setCellValue("PRICE");
        cell = row.createCell(7);
        cell.setCellValue("ITEM CODE");
        int i = 2;


        while(resultSet.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(transferService.getFromViaInt(resultSet.getInt("to")));
            cell = row.createCell(2);
            cell.setCellValue(resultSet.getDate("date"));
            cell = row.createCell(3);
            cell.setCellValue(resultSet.getString("item"));
            cell = row.createCell(4);
            cell.setCellValue(resultSet.getDouble("amount"));
            cell = row.createCell(5);
            cell.setCellValue(resultSet.getString("senderName"));
            cell = row.createCell(6);
            cell.setCellValue(resultSet.getDouble("totalPrice"));
            cell = row.createCell(7);
            cell.setCellValue(resultSet.getInt("itemCode"));
            i++;
        }
        FileOutputStream out = new FileOutputStream(f);
        workbook.write(out);
        out.close();
        log.info("written: "+sheetname);

    }

    public void writeAll(String s)throws SQLException, ClassNotFoundException, IOException{



        LocalDate date = YearMonth.now().atDay( 1 );


        write(1, date);
        write(2, date);
        write(3, date);
        write(4, date);
        write(5, date);
        write(6, date);
        write(7, date);
        write(8, date);
        write(9, date);
        write(10, date);
        write(11, date);
        write(12, date);
        write(13, date);

    }



}
