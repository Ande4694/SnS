package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.service.UserDetailsServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TransferRepoImpl  implements TransferRepo{

    @Autowired
    Transfer transfer;
    @Autowired
    JdbcTemplate template;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    UserDetailsServiceImpl user;

    int department;
    String departmentto;



    @Override
    public List<Transfer> getTransfersFrom(int dep){

        List<Transfer> outgoingTransfers;


        String sql = "SELECT * FROM sns.sendings WHERE `from`=?";

        RowMapper<Transfer> rm = new BeanPropertyRowMapper<>(Transfer.class);
        outgoingTransfers = template.query(sql, rm, dep);


        return outgoingTransfers;

    }

    @Override
    public String getToFromViaInt(int id){

        if (id==1){
            return departmentto ="Nansensgade";
        }
        if (id==2){
            return departmentto ="Hellerup";
        }
        if (id==3){
            return departmentto ="Østerbro";
        }
        if (id==4){
            return departmentto ="Istedgade";
        }
        if (id==5){
            return departmentto ="Gammel Kongevej";
        }
        if (id==6){
            return departmentto ="Valby";
        }
        if (id==7){
            return departmentto ="Lyngby";
        }
        if (id==8){
            return departmentto ="Tivoli Hotel";
        }
        if (id==9){
            return departmentto ="Rungsted";
        }
        if (id==10){
            return departmentto ="Borgergade";
        }
        if (id==11){
            return departmentto ="Krudthuset";
        }
        if (id==12){
            return departmentto ="Tivoli Gardens";
        }
        if (id==13){
            return departmentto ="Baghuset";
        }
        return departmentto = "error loading";
    }


    @Override
    public int getToViaUsername(String username){


        if (user.getUsername().equals("nansens")){
            return department = 1;
        }
        if (user.getUsername().equals("hell")){
            return department = 2;
        }
        if (user.getUsername().equals("øst")){
            return department = 3;
        }
        if (user.getUsername().equals("istedgade")){
            return department = 4;
        }
        if (user.getUsername().equals("glk")){
            return department = 5;
        }
        if (user.getUsername().equals("valby")){
            return department = 6;
        }
        if (user.getUsername().equals("lyngby")){
            return department = 7;
        }
        if (user.getUsername().equals("hotel")){
            return department = 8;
        }
        if (user.getUsername().equals("rungsted")){
            return department = 9;
        }
        if (user.getUsername().equals("borgergade")){
            return department = 10;
        }
        if (user.getUsername().equals("krudthuset")){
            return department = 11;
        }
        if (user.getUsername().equals("garden")){
            return department = 12;
        }
        if (user.getUsername().equals("finans")){
            return department = 13;
        }

        return department;
    }

    @Override
    public int getToFromViaName(String name){


        if (name.equals("Gammel Kongevej")){
             return department = 5;
        }
        if (name.equals("Nansensgade")){
            return department = 1;
        }
        if (name.equals("Hellerup")){
            return department = 2;
        }
        if (name.equals("Østerbro")){
            return department = 3;
        }
        if (name.equals("Istedgade")){
            return department = 4;
        }
        if (name.equals("Valby")){
            return department = 6;
        }
        if (name.equals("Tivoli Hotel")){
            return department = 8;
        }
        if (name.equals("Lyngby")){
            return department = 7;
        }
        if (name.equals("Rungsted")){
            return department = 9;
        }
        if (name.equals("Borgergade")){
            return department = 10;
        }
        if (name.equals("Krudthuset")){
            return department = 11;
        }
        if (name.equals("Tivoli Garden")){
            return department = 12;
        }
        if (name.equals("Baghuset")){
            return department = 13;
        }

        return department;
    }

    @Override
    public void deleteTransfer(int id){
        String sql = "DELETE FROM sns.sendings WHERE idSendings=?";

        this.template.update(sql, id);
    }

    @Override
    public void sendItem(Transfer transfer)  {
        String sql ="INSERT INTO sns.sendings  VALUES (default ,?,?,?,?,?,?,?,?)";



        int to = getToFromViaName(transfer.getTo());
        int from = getToViaUsername(transfer.getFrom());
        Date date = transfer.getSendingDate();
        String item = transfer.getItem();
        String sender = transfer.getSenderName();
        Double amount = transfer.getAmount();

        double totalPrice = amount* itemRepo.getItem(item).getUnitPrice();
        int itemCode = itemRepo.getItem(item).getItemCode();

        this.template.update(sql, from, to, date, item, amount, sender, totalPrice, itemCode);


}

    @Override
    public List<Transfer> getAllTransfers(){
        String sql ="SELECT * FROM sns.sendings";

        return this.template.query(sql, new ResultSetExtractor<List<Transfer>>() {
            @Override
            public List<Transfer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id, from, to, itemCode;
                String item, senderName;
                double totalPrice, amount;
                Date sendingDate;


                ArrayList<Transfer> allTransfers = new ArrayList<>();

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
                    String fromDep = getToFromViaInt(from);
                    String toDep = getToFromViaInt(to);


                    allTransfers.add(new Transfer(id, fromDep, toDep, sendingDate, item, totalPrice, itemCode, senderName, amount));
                }
                return allTransfers;
            }
        });
    }

    @Override
    public Transfer selectTransfer(int id) {
        String sql = "SELECT * FROM sns.sendings WHERE idSendings=?";

        RowMapper<Transfer> rm = new BeanPropertyRowMapper<>(Transfer.class);
        Transfer transfer = template.queryForObject(sql, rm, id);
        return transfer;

    }

    @Override
    public void updateTransfer(Transfer transfer, int id) {


        String sql = "UPDATE sns.sendings " +
                "SET `from`=?, `to`=?, date=?, item=?, totalPrice=?, itemCode=?, senderName=?, amount=? " +
                "WHERE idSendings =" + id;



        int from =getToFromViaName(transfer.getFrom());
        int to = getToFromViaName(transfer.getTo());


        this.template.update(sql, from, to, transfer.getSendingDate(),transfer.getItem(), transfer.getTotalPrice(), transfer.getItemCode(), transfer.getSenderName(), transfer.getAmount());


    }

    @Override
    public List<Transfer> searchTransferByDep(String from) {

        String sql = "SELECT * FROM sns.sendings WHERE `from` =?";

        RowMapper<Transfer> rm = new BeanPropertyRowMapper<>(Transfer.class);

        List<Transfer> searched = template.query(sql, rm, from);
        return searched;
    }




}