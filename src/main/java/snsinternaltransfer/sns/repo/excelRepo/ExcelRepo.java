package snsinternaltransfer.sns.repo.excelRepo;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.controller.MainController;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.service.TransferService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
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


    public void write(int dep, Date after)throws SQLException, ClassNotFoundException, IOException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://snsgrp5k.ctjynaaxvgot.eu-central-1.rds.amazonaws.com:3306/sns" ,
                "snsgrp5k" ,
                "snsgrp5k"
        );

        File f = new File("exceldatabase.xlsx");
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sns.sendings where `from`= "+dep+" and date >"+ after);
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetname = transferService.getFromViaInt(dep);
        XSSFSheet spreadsheet = workbook.createSheet("sheetname");

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
            cell.setCellValue(resultSet.getInt("TO"));
            cell = row.createCell(2);
            cell.setCellValue(resultSet.getDate("DATE"));
            cell = row.createCell(3);
            cell.setCellValue(resultSet.getString("ITEM"));
            cell = row.createCell(4);
            cell.setCellValue(resultSet.getDouble("AMOUNT"));
            cell = row.createCell(5);
            cell.setCellValue(resultSet.getString("SENDERS NAME"));
            cell = row.createCell(6);
            cell.setCellValue(resultSet.getDouble("PRICE"));
            cell = row.createCell(7);
            cell.setCellValue(resultSet.getInt("ITEM CODE"));
            i++;
        }
        FileOutputStream out = new FileOutputStream(f);
        workbook.write(out);
        out.close();
        log.info("written: "+sheetname);

    }

    public void writeAll(Date after)throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
        write(1, after);
        write(2, after);
        write(3, after);
        write(4, after);
        write(5, after);
        write(6, after);
        write(7, after);
        write(8, after);
        write(9, after);
        write(10, after);
        write(11, after);
        write(12, after);
        write(13, after);

    }



}
