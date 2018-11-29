package snsinternaltransfer.sns.repo.login;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppRoleDAO {

    @Autowired
    JdbcTemplate template;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.Role_Name " //
                + " from sns.user_role ur, sns.app_role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";

        Object[] params = new Object[] { userId };

        List<String> roles = this.template.queryForList(sql, params, String.class);

        return roles;
    }

}