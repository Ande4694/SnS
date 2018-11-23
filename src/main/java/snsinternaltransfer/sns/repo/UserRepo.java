package snsinternaltransfer.sns.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepo {

    @Autowired
    User user;
    JdbcTemplate template;


    public User getUserViaUsername(String username){
        String sql = "SELECT * FROM sns.users WHERE username=?";

        User user = new User();

        RowMapper<User> rm = new BeanPropertyRowMapper<>(User.class);
        user = template.queryForObject(sql, rm, username);
        return user;
    }





    public User findLogin(String username, String password) {
        String sql = "SELECT * FROM sns.users WHERE username = ? AND password = ?";

        return this.template.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                String username, password, salt;
                int id, userState;
                User user = new User();

                while (rs.next()) {
                    username = rs.getString("username");
                    password = rs.getString("password");
                    id = rs.getInt("userId");
                    userState = rs.getInt("state");
                    salt = rs.getString("salt");

                    user.setUsername(username);
                    user.setPassword(password);
                    user.setId(id);
                    user.setUserState(userState);
                    user.setSalt(salt);
                }
                return user;
            }
        },username, password);
    }
}