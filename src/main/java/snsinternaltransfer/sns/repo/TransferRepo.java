package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Department;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;


import javax.sql.DataSource;
import java.util.Date;

@Repository
public class TransferRepo  {

    @Autowired
    Transfer transfer;
    JdbcTemplate template;
    ItemRepo itemRepo;







    public Transfer sendItem(String to, String from, String date, String item, String sender, double amount)  {
        String sql ="INSERT INTO sns.sendings  VALUES (default ,?,?,?,?,?,?,?,?)";


        double totalPrice = amount* itemRepo.getItem(item).getUnitPrice();
        int itemCode = itemRepo.getItem(item).getItemCode();


        /// userdetails app.user    this.appuser = name



        this.template.update(sql, from, to, date, item, totalPrice, itemCode, sender, amount);

        return transfer;

}




}