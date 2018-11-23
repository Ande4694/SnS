package snsinternaltransfer.sns.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.User;

@Repository
public class UserRepo {

    @Autowired
    User user;
    JdbcTemplate template;


    public User getUserViaUsername(String username){
        String sql = "SELECT * FROM sns.users WHERE username=?";
        RowMapper<User> rm = new BeanPropertyRowMapper<>(User.class);
        User user = template.queryForObject(sql, rm, username);
        return user;
    }


    public String getPasswordViaUsername(String username){
        String sql = "SELECT password FROM sns.users WHERE username=?";
        RowMapper<String> rm = new BeanPropertyRowMapper<>(String.class);
        String hashedPassword = template.queryForObject(sql, rm, username);
        return hashedPassword;
    }


    public String getSaltViaUsername(String username){
        String sql = "SELECT salt FROM sns.users WHERE username=?";
        RowMapper<String> rm = new BeanPropertyRowMapper<>(String.class);
        String salt = template.queryForObject(sql, rm, username);
        return salt;
    }
}