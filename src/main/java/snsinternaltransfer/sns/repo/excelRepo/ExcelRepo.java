package snsinternaltransfer.sns.repo.excelRepo;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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


    public void writeAll(String s)throws IOException, SQLException, ClassNotFoundException{

        LocalDate date = YearMonth.now().atDay( 1 );

        File f = new File("TransferSheet.xlsx");
        FileOutputStream out = new FileOutputStream(f);

        XSSFWorkbook workbook = new XSSFWorkbook();

        for (int i = 1; i <= 13; i++){

            writeOneDep(i, date);

        }

        workbook.write(out);
        out.close();


    }


    public void writeOneDep(int dep, LocalDate after)throws SQLException, ClassNotFoundException{

        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://snsgrp5k.ctjynaaxvgot.eu-central-1.rds.amazonaws.com:3306/sns" ,
                "snsgrp5k" ,
                "snsgrp5k"
        );

        Statement statement = connect.createStatement();
        ResultSet resultSetnan = statement.executeQuery("select * from sns.sendings where `from`= "+dep+" and date >"+ after);


        //////////////////////////////////////////////
        XSSFWorkbook workbook = new XSSFWorkbook();

        String sheetname = transferService.getFromViaInt(dep);
        XSSFSheet nan = workbook.createSheet(sheetname);
        ////////////////////////////////////////////////



        XSSFRow row = nan.createRow(1);
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
        int i = 3;


        while(resultSetnan.next()) {
            row = nan.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(transferService.getFromViaInt(resultSetnan.getInt("to")));
            cell = row.createCell(2);
            cell.setCellValue(resultSetnan.getDate("date"));
            cell = row.createCell(3);
            cell.setCellValue(resultSetnan.getString("item"));
            cell = row.createCell(4);
            cell.setCellValue(resultSetnan.getDouble("amount"));
            cell = row.createCell(5);
            cell.setCellValue(resultSetnan.getString("senderName"));
            cell = row.createCell(6);
            cell.setCellValue(resultSetnan.getDouble("totalPrice"));
            cell = row.createCell(7);
            cell.setCellValue(resultSetnan.getInt("itemCode"));
            i++;
        }
    }


    public void write(String s)throws SQLException, ClassNotFoundException, IOException {

        LocalDate date = YearMonth.now().atDay( 1 );

        File f = new File("TransferSheet.xlsx");
        FileOutputStream out = new FileOutputStream(f);

        XSSFWorkbook workbook = new XSSFWorkbook();


        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://snsgrp5k.ctjynaaxvgot.eu-central-1.rds.amazonaws.com:3306/sns" ,
                "snsgrp5k" ,
                "snsgrp5k"
        );

        Statement statement = connect.createStatement();
        ResultSet resultSetnan = statement.executeQuery("select * from sns.sendings where `from`= 1 and date >"+ date);
        ResultSet resultSethel = statement.executeQuery("select * from sns.sendings where `from`= 2 and date >"+ date);
        ResultSet resultSetøst = statement.executeQuery("select * from sns.sendings where `from`= 3 and date >"+ date);
        ResultSet resultSetist = statement.executeQuery("select * from sns.sendings where `from`= 4 and date >"+ date);
        ResultSet resultSetglk = statement.executeQuery("select * from sns.sendings where `from`= 5 and date >"+ date);
        ResultSet resultSetval = statement.executeQuery("select * from sns.sendings where `from`= 6 and date >"+ date);
        ResultSet resultSetlyn = statement.executeQuery("select * from sns.sendings where `from`= 7 and date >"+ date);
        ResultSet resultSethot = statement.executeQuery("select * from sns.sendings where `from`= 8 and date >"+ date);
        ResultSet resultSetrun = statement.executeQuery("select * from sns.sendings where `from`= 9 and date >"+ date);
        ResultSet resultSetbor = statement.executeQuery("select * from sns.sendings where `from`= 10 and date >"+ date);
        ResultSet resultSetkru = statement.executeQuery("select * from sns.sendings where `from`= 11 and date >"+ date);
        ResultSet resultSetgar = statement.executeQuery("select * from sns.sendings where `from`= 12 and date >"+ date);
        ResultSet resultSetbag = statement.executeQuery("select * from sns.sendings where `from`= 13 and date >"+ date);


        XSSFSheet nan = workbook.createSheet(transferService.getFromViaInt(1));
        XSSFSheet hel = workbook.createSheet(transferService.getFromViaInt(2));
        XSSFSheet øst = workbook.createSheet(transferService.getFromViaInt(3));
        XSSFSheet ist = workbook.createSheet(transferService.getFromViaInt(4));
        XSSFSheet glk = workbook.createSheet(transferService.getFromViaInt(5));
        XSSFSheet val = workbook.createSheet(transferService.getFromViaInt(6));
        XSSFSheet lyn = workbook.createSheet(transferService.getFromViaInt(7));
        XSSFSheet hot = workbook.createSheet(transferService.getFromViaInt(8));
        XSSFSheet run = workbook.createSheet(transferService.getFromViaInt(9));
        XSSFSheet bor = workbook.createSheet(transferService.getFromViaInt(10));
        XSSFSheet kru = workbook.createSheet(transferService.getFromViaInt(11));
        XSSFSheet gar = workbook.createSheet(transferService.getFromViaInt(12));
        XSSFSheet bag = workbook.createSheet(transferService.getFromViaInt(13));

        XSSFRow row = nan.createRow(1);
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


        while(resultSetnan.next()) {
            row = nan.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(transferService.getFromViaInt(resultSetnan.getInt("to")));
            cell = row.createCell(2);
            cell.setCellValue(resultSetnan.getDate("date"));
            cell = row.createCell(3);
            cell.setCellValue(resultSetnan.getString("item"));
            cell = row.createCell(4);
            cell.setCellValue(resultSetnan.getDouble("amount"));
            cell = row.createCell(5);
            cell.setCellValue(resultSetnan.getString("senderName"));
            cell = row.createCell(6);
            cell.setCellValue(resultSetnan.getDouble("totalPrice"));
            cell = row.createCell(7);
            cell.setCellValue(resultSetnan.getInt("itemCode"));
            i++;
        }
            ///////////////////////////////////////////////////////////////////

        XSSFRow row1 = hel.createRow(1);
        XSSFCell cell1;
        cell1 = row1.createCell(1);
        cell1.setCellValue("TO");
        cell1 = row1.createCell(2);
        cell1.setCellValue("DATE");
        cell1 = row1.createCell(3);
        cell1.setCellValue("ITEM");
        cell1 = row1.createCell(4);
        cell1.setCellValue("AMOUNT");
        cell1 = row1.createCell(5);
        cell1.setCellValue("SENDERS NAME");
        cell1 = row1.createCell(6);
        cell1.setCellValue("PRICE");
        cell1 = row1.createCell(7);
        cell1.setCellValue("ITEM CODE");
        i = 2;


        while(resultSethel.next()) {
            row1 = hel.createRow(i);
            cell1 = row1.createCell(1);
            cell1.setCellValue(transferService.getFromViaInt(resultSethel.getInt("to")));
            cell1 = row1.createCell(2);
            cell1.setCellValue(resultSethel.getDate("date"));
            cell1 = row1.createCell(3);
            cell1.setCellValue(resultSethel.getString("item"));
            cell1 = row1.createCell(4);
            cell1.setCellValue(resultSethel.getDouble("amount"));
            cell1 = row1.createCell(5);
            cell1.setCellValue(resultSethel.getString("senderName"));
            cell1 = row1.createCell(6);
            cell1.setCellValue(resultSethel.getDouble("totalPrice"));
            cell1 = row1.createCell(7);
            cell1.setCellValue(resultSethel.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777


        XSSFRow row2 = øst.createRow(1);
        XSSFCell cell2;
        cell2 = row2.createCell(1);
        cell2.setCellValue("TO");
        cell2 = row2.createCell(2);
        cell2.setCellValue("DATE");
        cell2 = row2.createCell(3);
        cell2.setCellValue("ITEM");
        cell2 = row2.createCell(4);
        cell2.setCellValue("AMOUNT");
        cell2 = row2.createCell(5);
        cell2.setCellValue("SENDERS NAME");
        cell2 = row2.createCell(6);
        cell2.setCellValue("PRICE");
        cell2 = row2.createCell(7);
        cell2.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetøst.next()) {
            row2 = øst.createRow(i);
            cell2 = row2.createCell(1);
            cell2.setCellValue(transferService.getFromViaInt(resultSetøst.getInt("to")));
            cell2 = row2.createCell(2);
            cell2.setCellValue(resultSetøst.getDate("date"));
            cell2 = row2.createCell(3);
            cell2.setCellValue(resultSetøst.getString("item"));
            cell2 = row2.createCell(4);
            cell2.setCellValue(resultSetøst.getDouble("amount"));
            cell2 = row2.createCell(5);
            cell2.setCellValue(resultSetøst.getString("senderName"));
            cell2 = row2.createCell(6);
            cell2.setCellValue(resultSetøst.getDouble("totalPrice"));
            cell2 = row2.createCell(7);
            cell2.setCellValue(resultSetøst.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777


        XSSFRow row3 = ist.createRow(1);
        XSSFCell cell3;
        cell3 = row3.createCell(1);
        cell3.setCellValue("TO");
        cell3 = row3.createCell(2);
        cell3.setCellValue("DATE");
        cell3 = row3.createCell(3);
        cell3.setCellValue("ITEM");
        cell3 = row3.createCell(4);
        cell3.setCellValue("AMOUNT");
        cell3 = row3.createCell(5);
        cell3.setCellValue("SENDERS NAME");
        cell3 = row3.createCell(6);
        cell3.setCellValue("PRICE");
        cell3 = row3.createCell(7);
        cell3.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetist.next()) {
            row3 = ist.createRow(i);
            cell3 = row3.createCell(1);
            cell3.setCellValue(transferService.getFromViaInt(resultSetist.getInt("to")));
            cell3 = row3.createCell(2);
            cell3.setCellValue(resultSetist.getDate("date"));
            cell3 = row3.createCell(3);
            cell3.setCellValue(resultSetist.getString("item"));
            cell3 = row3.createCell(4);
            cell3.setCellValue(resultSetist.getDouble("amount"));
            cell3 = row3.createCell(5);
            cell3.setCellValue(resultSetist.getString("senderName"));
            cell3 = row3.createCell(6);
            cell3.setCellValue(resultSetist.getDouble("totalPrice"));
            cell3 = row3.createCell(7);
            cell3.setCellValue(resultSetist.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777


        XSSFRow row4 = glk.createRow(1);
        XSSFCell cell4;
        cell4 = row4.createCell(1);
        cell4.setCellValue("TO");
        cell4 = row4.createCell(2);
        cell4.setCellValue("DATE");
        cell4 = row4.createCell(3);
        cell4.setCellValue("ITEM");
        cell4 = row4.createCell(4);
        cell4.setCellValue("AMOUNT");
        cell4 = row4.createCell(5);
        cell4.setCellValue("SENDERS NAME");
        cell4 = row4.createCell(6);
        cell4.setCellValue("PRICE");
        cell4 = row4.createCell(7);
        cell4.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetglk.next()) {
            row4 = glk.createRow(i);
            cell4 = row4.createCell(1);
            cell4.setCellValue(transferService.getFromViaInt(resultSetglk.getInt("to")));
            cell4 = row4.createCell(2);
            cell4.setCellValue(resultSetglk.getDate("date"));
            cell4 = row4.createCell(3);
            cell4.setCellValue(resultSetglk.getString("item"));
            cell4 = row4.createCell(4);
            cell4.setCellValue(resultSetglk.getDouble("amount"));
            cell4 = row4.createCell(5);
            cell4.setCellValue(resultSetglk.getString("senderName"));
            cell4 = row4.createCell(6);
            cell4.setCellValue(resultSetglk.getDouble("totalPrice"));
            cell4 = row4.createCell(7);
            cell4.setCellValue(resultSetglk.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row5 = val.createRow(1);
        XSSFCell cell5;
        cell5 = row5.createCell(1);
        cell5.setCellValue("TO");
        cell5 = row5.createCell(2);
        cell5.setCellValue("DATE");
        cell5 = row5.createCell(3);
        cell5.setCellValue("ITEM");
        cell5 = row5.createCell(4);
        cell5.setCellValue("AMOUNT");
        cell5 = row5.createCell(5);
        cell5.setCellValue("SENDERS NAME");
        cell5 = row5.createCell(6);
        cell5.setCellValue("PRICE");
        cell5 = row5.createCell(7);
        cell5.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetval.next()) {
            row5 = val.createRow(i);
            cell5 = row5.createCell(1);
            cell5.setCellValue(transferService.getFromViaInt(resultSetval.getInt("to")));
            cell5 = row5.createCell(2);
            cell5.setCellValue(resultSetval.getDate("date"));
            cell5 = row5.createCell(3);
            cell5.setCellValue(resultSetval.getString("item"));
            cell5 = row5.createCell(4);
            cell5.setCellValue(resultSetval.getDouble("amount"));
            cell5 = row5.createCell(5);
            cell5.setCellValue(resultSetval.getString("senderName"));
            cell5 = row5.createCell(6);
            cell5.setCellValue(resultSetval.getDouble("totalPrice"));
            cell5 = row5.createCell(7);
            cell5.setCellValue(resultSetval.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777


        XSSFRow row6 = lyn.createRow(1);
        XSSFCell cell6;
        cell6 = row6.createCell(1);
        cell6.setCellValue("TO");
        cell6 = row6.createCell(2);
        cell6.setCellValue("DATE");
        cell6 = row6.createCell(3);
        cell6.setCellValue("ITEM");
        cell6 = row6.createCell(4);
        cell6.setCellValue("AMOUNT");
        cell6 = row6.createCell(5);
        cell6.setCellValue("SENDERS NAME");
        cell6 = row6.createCell(6);
        cell6.setCellValue("PRICE");
        cell6 = row6.createCell(7);
        cell6.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetlyn.next()) {
            row6 = lyn.createRow(i);
            cell6 = row6.createCell(1);
            cell6.setCellValue(transferService.getFromViaInt(resultSetlyn.getInt("to")));
            cell6 = row6.createCell(2);
            cell6.setCellValue(resultSetlyn.getDate("date"));
            cell6 = row6.createCell(3);
            cell6.setCellValue(resultSetlyn.getString("item"));
            cell6 = row6.createCell(4);
            cell6.setCellValue(resultSetlyn.getDouble("amount"));
            cell6 = row6.createCell(5);
            cell6.setCellValue(resultSetlyn.getString("senderName"));
            cell6 = row6.createCell(6);
            cell6.setCellValue(resultSetlyn.getDouble("totalPrice"));
            cell6 = row6.createCell(7);
            cell6.setCellValue(resultSetlyn.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row7 = hot.createRow(1);
        XSSFCell cell7;
        cell7 = row7.createCell(1);
        cell7.setCellValue("TO");
        cell7 = row7.createCell(2);
        cell7.setCellValue("DATE");
        cell7 = row7.createCell(3);
        cell7.setCellValue("ITEM");
        cell7 = row7.createCell(4);
        cell7.setCellValue("AMOUNT");
        cell7 = row7.createCell(5);
        cell7.setCellValue("SENDERS NAME");
        cell7 = row7.createCell(6);
        cell7.setCellValue("PRICE");
        cell7 = row7.createCell(7);
        cell7.setCellValue("ITEM CODE");
        i = 2;


        while(resultSethot.next()) {
            row7 = hot.createRow(i);
            cell7 = row7.createCell(1);
            cell7.setCellValue(transferService.getFromViaInt(resultSethot.getInt("to")));
            cell7 = row7.createCell(2);
            cell7.setCellValue(resultSethot.getDate("date"));
            cell7 = row7.createCell(3);
            cell7.setCellValue(resultSethot.getString("item"));
            cell7 = row7.createCell(4);
            cell7.setCellValue(resultSethot.getDouble("amount"));
            cell7 = row7.createCell(5);
            cell7.setCellValue(resultSethot.getString("senderName"));
            cell7 = row7.createCell(6);
            cell7.setCellValue(resultSethot.getDouble("totalPrice"));
            cell7 = row7.createCell(7);
            cell7.setCellValue(resultSethot.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row8 = run.createRow(1);
        XSSFCell cell8;
        cell8 = row8.createCell(1);
        cell8.setCellValue("TO");
        cell8 = row8.createCell(2);
        cell8.setCellValue("DATE");
        cell8 = row8.createCell(3);
        cell8.setCellValue("ITEM");
        cell8 = row8.createCell(4);
        cell8.setCellValue("AMOUNT");
        cell8 = row8.createCell(5);
        cell8.setCellValue("SENDERS NAME");
        cell8 = row8.createCell(6);
        cell8.setCellValue("PRICE");
        cell8 = row8.createCell(7);
        cell8.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetrun.next()) {
            row8 = run.createRow(i);
            cell8 = row8.createCell(1);
            cell8.setCellValue(transferService.getFromViaInt(resultSetrun.getInt("to")));
            cell8 = row8.createCell(2);
            cell8.setCellValue(resultSetrun.getDate("date"));
            cell8 = row8.createCell(3);
            cell8.setCellValue(resultSetrun.getString("item"));
            cell8 = row8.createCell(4);
            cell8.setCellValue(resultSetrun.getDouble("amount"));
            cell8 = row8.createCell(5);
            cell8.setCellValue(resultSetrun.getString("senderName"));
            cell8 = row8.createCell(6);
            cell8.setCellValue(resultSetrun.getDouble("totalPrice"));
            cell8 = row8.createCell(7);
            cell8.setCellValue(resultSetrun.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row9 = bor.createRow(1);
        XSSFCell cell9;
        cell9 = row9.createCell(1);
        cell9.setCellValue("TO");
        cell9 = row9.createCell(2);
        cell9.setCellValue("DATE");
        cell9 = row9.createCell(3);
        cell9.setCellValue("ITEM");
        cell9 = row9.createCell(4);
        cell9.setCellValue("AMOUNT");
        cell9 = row9.createCell(5);
        cell9.setCellValue("SENDERS NAME");
        cell9 = row9.createCell(6);
        cell9.setCellValue("PRICE");
        cell9 = row9.createCell(7);
        cell9.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetbor.next()) {
            row9 = bor.createRow(i);
            cell9 = row9.createCell(1);
            cell9.setCellValue(transferService.getFromViaInt(resultSetbor.getInt("to")));
            cell9 = row9.createCell(2);
            cell9.setCellValue(resultSetbor.getDate("date"));
            cell9 = row9.createCell(3);
            cell9.setCellValue(resultSetbor.getString("item"));
            cell9 = row9.createCell(4);
            cell9.setCellValue(resultSetbor.getDouble("amount"));
            cell9 = row9.createCell(5);
            cell9.setCellValue(resultSetbor.getString("senderName"));
            cell9 = row9.createCell(6);
            cell9.setCellValue(resultSetbor.getDouble("totalPrice"));
            cell9 = row9.createCell(7);
            cell9.setCellValue(resultSetbor.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row10 = kru.createRow(1);
        XSSFCell cell10;
        cell10 = row10.createCell(1);
        cell10.setCellValue("TO");
        cell10 = row10.createCell(2);
        cell10.setCellValue("DATE");
        cell10 = row10.createCell(3);
        cell10.setCellValue("ITEM");
        cell10 = row10.createCell(4);
        cell10.setCellValue("AMOUNT");
        cell10 = row10.createCell(5);
        cell10.setCellValue("SENDERS NAME");
        cell10 = row10.createCell(6);
        cell10.setCellValue("PRICE");
        cell10 = row10.createCell(7);
        cell10.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetkru.next()) {
            row10 = kru.createRow(i);
            cell10 = row10.createCell(1);
            cell10.setCellValue(transferService.getFromViaInt(resultSetkru.getInt("to")));
            cell10 = row10.createCell(2);
            cell10.setCellValue(resultSetkru.getDate("date"));
            cell10 = row10.createCell(3);
            cell10.setCellValue(resultSetkru.getString("item"));
            cell10 = row10.createCell(4);
            cell10.setCellValue(resultSetkru.getDouble("amount"));
            cell10 = row10.createCell(5);
            cell10.setCellValue(resultSetkru.getString("senderName"));
            cell10 = row10.createCell(6);
            cell10.setCellValue(resultSetkru.getDouble("totalPrice"));
            cell10 = row10.createCell(7);
            cell10.setCellValue(resultSetkru.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row11 = gar.createRow(1);
        XSSFCell cell11;
        cell11 = row11.createCell(1);
        cell11.setCellValue("TO");
        cell11 = row11.createCell(2);
        cell11.setCellValue("DATE");
        cell11 = row11.createCell(3);
        cell11.setCellValue("ITEM");
        cell11 = row11.createCell(4);
        cell11.setCellValue("AMOUNT");
        cell11 = row11.createCell(5);
        cell11.setCellValue("SENDERS NAME");
        cell11 = row11.createCell(6);
        cell11.setCellValue("PRICE");
        cell11 = row11.createCell(7);
        cell11.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetgar.next()) {
            row11 = gar.createRow(i);
            cell11 = row11.createCell(1);
            cell11.setCellValue(transferService.getFromViaInt(resultSetgar.getInt("to")));
            cell11 = row11.createCell(2);
            cell11.setCellValue(resultSetgar.getDate("date"));
            cell11 = row11.createCell(3);
            cell11.setCellValue(resultSetgar.getString("item"));
            cell11 = row11.createCell(4);
            cell11.setCellValue(resultSetgar.getDouble("amount"));
            cell11 = row11.createCell(5);
            cell11.setCellValue(resultSetgar.getString("senderName"));
            cell11 = row11.createCell(6);
            cell11.setCellValue(resultSetgar.getDouble("totalPrice"));
            cell11 = row11.createCell(7);
            cell11.setCellValue(resultSetgar.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        XSSFRow row12 = bag.createRow(1);
        XSSFCell cell12;
        cell12 = row12.createCell(1);
        cell12.setCellValue("TO");
        cell12 = row12.createCell(2);
        cell12.setCellValue("DATE");
        cell12 = row12.createCell(3);
        cell12.setCellValue("ITEM");
        cell12 = row12.createCell(4);
        cell12.setCellValue("AMOUNT");
        cell12 = row12.createCell(5);
        cell12.setCellValue("SENDERS NAME");
        cell12 = row12.createCell(6);
        cell12.setCellValue("PRICE");
        cell12 = row12.createCell(7);
        cell12.setCellValue("ITEM CODE");
        i = 2;


        while(resultSetbag.next()) {
            row12 = nan.createRow(i);
            cell12 = row12.createCell(1);
            cell12.setCellValue(transferService.getFromViaInt(resultSetbag.getInt("to")));
            cell12 = row12.createCell(2);
            cell12.setCellValue(resultSetbag.getDate("date"));
            cell12 = row12.createCell(3);
            cell12.setCellValue(resultSetbag.getString("item"));
            cell12 = row12.createCell(4);
            cell12.setCellValue(resultSetbag.getDouble("amount"));
            cell12 = row12.createCell(5);
            cell12.setCellValue(resultSetbag.getString("senderName"));
            cell12 = row12.createCell(6);
            cell12.setCellValue(resultSetbag.getDouble("totalPrice"));
            cell12 = row12.createCell(7);
            cell12.setCellValue(resultSetbag.getInt("itemCode"));
            i++;
        }
        ///////////////////////////////////////////////////////////777

        workbook.write(out);
        out.close();
    }





}
