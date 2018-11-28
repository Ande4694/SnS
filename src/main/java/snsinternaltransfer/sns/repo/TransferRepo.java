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





    public int getToFromViaName(String name){
        int department = 0;

        if (name == "Gammel Kongevej"){
            department = 5;
        }
        if (name == "Nansensgade"){
            department = 1;
        }
        if (name == "Hellerup"){
            department = 2;
        }
        if (name == "Østerbro"){
            department = 3;
        }
        if (name == "Istedgade"){
            department = 4;
        }
        if (name == "Valby"){
            department = 6;
        }
        if (name == "Tivoli Hotel"){
            department = 8;
        }
        if (name == "Lyngby"){
            department = 7;
        }
        if (name == "Rungsted"){
            department = 9;
        }
        if (name == "Borgergade"){
            department = 10;
        }
        if (name == "Krudthuset"){
            department = 11;
        }
        if (name == "Tivoli Garden"){
            department = 12;
        }
        if (name == "Baghuset"){
            department = 13;
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