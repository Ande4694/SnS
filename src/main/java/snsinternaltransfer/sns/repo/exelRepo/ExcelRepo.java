package snsinternaltransfer.sns.repo.exelRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.controller.MainController;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;
import snsinternaltransfer.sns.utility.ExcelUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ExcelRepo {

    @Autowired
    ExcelUtils excelUtils;

    @Autowired
    JdbcTemplate template;

    @Autowired
    TransferRepo transferRepo;

    private final Logger log = Logger.getLogger(MainController.class.getName());

    public List<Transfer> getAllTransfersFromCertainDep(int dep){
        LocalDate after = YearMonth.now().atDay( 1 );
        String sql ="select * from sns.sendings where `from`= "+dep+" and date > "+ "'"+after.toString()+"'";

        return this.template.query(sql, new ResultSetExtractor<List<Transfer>>() {
            @Override
            public List<Transfer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id, from, to, itemCode;
                String item, senderName;
                double totalPrice, amount;
                Date sendingDate;


                List<Transfer> allTransfers = new ArrayList<>();

                while (rs.next()) {
                    id = rs.getInt("idSendings");
                    from = rs.getInt("from");
                    to = rs.getInt("to");
                    sendingDate = rs.getDate("date");
                    item = rs.getString("item");
                    totalPrice = rs.getDouble("totalPrice");
                    itemCode = rs.getInt("itemCode");
                    senderName = rs.getString("senderName");
                    amount = rs.getDouble("amount");


                    // convert fra int til string i to / from
                    String fromDep = transferRepo.getToFromViaInt(from);
                    String toDep = transferRepo.getToFromViaInt(to);


                    allTransfers.add(new Transfer(id, fromDep, toDep, sendingDate, item, totalPrice, itemCode, senderName, amount));
                }
                return allTransfers;
            }
        });
    }

    public void writeAllToExcel(String s){

        List<Transfer> nan = getAllTransfersFromCertainDep(1);
        List<Transfer> hel = getAllTransfersFromCertainDep(2);
        List<Transfer> øst = getAllTransfersFromCertainDep(3);
        List<Transfer> ist = getAllTransfersFromCertainDep(4);
        List<Transfer> glk = getAllTransfersFromCertainDep(5);
        List<Transfer> val = getAllTransfersFromCertainDep(6);
        List<Transfer> lyn = getAllTransfersFromCertainDep(7);
        List<Transfer> hot = getAllTransfersFromCertainDep(8);
        List<Transfer> run = getAllTransfersFromCertainDep(9);
        List<Transfer> bor = getAllTransfersFromCertainDep(10);
        List<Transfer> kru = getAllTransfersFromCertainDep(11);
        List<Transfer> gar = getAllTransfersFromCertainDep(12);
        List<Transfer> bag = getAllTransfersFromCertainDep(13);


        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Nansensgade", nan);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Hellerup", hel);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Østerbro", øst);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Istedgade", ist);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Gammel Kongevej", glk);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Valby", val);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Lyngby", lyn);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Tivoli Hotel", hot);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Rungsted", run);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Borgergade", bor);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Krudthuset", kru);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Tivoli Gardens", gar);
        excelUtils.writeToExcelInMultiSheets(System.getProperty("java.io.tmpdir")+"TransferSheet.xlsx", "Baghuset", bag);
    }


}
