package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Department;
import snsinternaltransfer.sns.models.Transfer;


import java.util.Date;

@Repository
public class TransferRepo{

    @Autowired
    Transfer transfer;
    JdbcTemplate template;
    ItemRepo itemRepo;
    DepartmentRepo depRep;





    public void sendItem(Transfer transfer, int senderId)  {
        String sql ="INSERT INTO sns.sendings VALUES (default, ?,?,?,?,?,?,?,?)";

        int from = senderId;
        int to = transfer.getTo();
        Date date = transfer.getSendingDate();
        int item = transfer.getItem().getId();
        double totalPrice = transfer.getAmount()* itemRepo.getItem(transfer.getItem().getName()).getUnitPrice();
        int itemCode = itemRepo.getItem(transfer.getItem().getName()).getItemCode();
        //laves om i DB
        String senderName = transfer.getSenderName();
        double amount = transfer.getAmount();

        this.template.update(sql, from, to, date, item, totalPrice, itemCode, senderName, amount);

    }




}