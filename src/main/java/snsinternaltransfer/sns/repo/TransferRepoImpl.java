package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.config.WebSecurityConfig;
import snsinternaltransfer.sns.models.Department;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class TransferRepoImpl implements TransferRepo{

    @Autowired
    Transfer transfer;
    JdbcTemplate template;
    WebSecurityConfig websec;

    int departmentId;

    @Override
    public int getSenderDepartment(){
        if (websec.userDetailsService().equals("glk")){
            departmentId = 5;
        }
        if (websec.userDetailsService().equals("nansens")){
            departmentId = 1;
        }
        if (websec.userDetailsService().equals("hell")){
            departmentId = 2;
        }
        if (websec.userDetailsService().equals("øst")){
            departmentId = 3;
        }
        if (websec.userDetailsService().equals("istedgade")){
            departmentId = 4;
        }
        if (websec.userDetailsService().equals("gardens")){
            departmentId = 12;
        }
        if (websec.userDetailsService().equals("valby")){
            departmentId = 6;
        }
        if (websec.userDetailsService().equals("lyngby")){
            departmentId = 7;
        }
        if (websec.userDetailsService().equals("hotel")){
            departmentId = 8;
        }
        if (websec.userDetailsService().equals("rungsted")){
            departmentId = 9;
        }
        if (websec.userDetailsService().equals("borgergade")){
            departmentId = 10;
        }
        if (websec.userDetailsService().equals("krudthuset")){
            departmentId = 11;
        }

        return departmentId;
    }

    @Override
    public void sendItem(Transfer transfer)  {
        String sql ="INSERT INTO sns.sendings VALUES (default, ?,?,?,?,?,?,?,?)";

        int from = getSenderDepartment();
        int to = transfer.getTo();
        Date date = transfer.getSendingDate();
        int item = transfer.getItem().getId();
        double totalPrice = transfer.getAmount()* getItem(transfer.getItem().getName()).getUnitPrice();
        int itemCode = getItem(transfer.getItem().getName()).getItemCode();
        String senderName = transfer.getSenderName();
        double amount = transfer.getAmount();

        this.template.update(sql, from, to, date, item, totalPrice, itemCode, senderName, amount);

        // get from skal "java" regner ud for os
    }

    @Override
    public Item getItem(String itemName) {
        String sql = "SELECT name, unitPrice, itemCode  FROM sns.items WHERE name="+itemName+
                "INNER JOIN itemcodes on sns.items.itemCode=itemcodes.iditemCodes";
                    // VI SKAL LIGE DOBBELTCHECKE DET HER INNER JOIN


        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);
        Item item = template.queryForObject(sql, rm);

        return item;
    }

    @Override
    public Department getDepartment(int departmentId) {
        String sql = "Select department FROM sns.department WHERE deartmentID="+departmentId;

        RowMapper<Department> rm = new BeanPropertyRowMapper<>(Department.class);
        Department department = template.queryForObject(sql, rm);

        return department;

    }
}
