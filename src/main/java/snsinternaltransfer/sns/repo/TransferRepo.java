package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.AppUser;
import snsinternaltransfer.sns.models.Department;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.login.AppUserDAO;
import snsinternaltransfer.sns.service.UserDetailsServiceImpl;


import javax.sql.DataSource;
import java.util.Date;

@Repository
public class TransferRepo  {

    @Autowired
    Transfer transfer;
    @Autowired
    JdbcTemplate template;
    @Autowired
    ItemRepo itemRepo;

    int department;





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



    public void sendItem(Transfer transfer)  {
        String sql ="INSERT INTO sns.sendings  VALUES (default ,?,?,?,?,?,?,?,?)";


        int to = getToFromViaName(transfer.getTo());
        int from = getToFromViaName(transfer.getFrom());
        Date date = transfer.getSendingDate();
        String item = transfer.getItem();
        String sender = transfer.getSenderName();
        Double amount = transfer.getAmount();

        double totalPrice = amount* itemRepo.getItem(item).getUnitPrice();
        int itemCode = itemRepo.getItem(item).getItemCode();

        this.template.update(sql, from, to, date, item, totalPrice, itemCode, sender, amount);


}




}