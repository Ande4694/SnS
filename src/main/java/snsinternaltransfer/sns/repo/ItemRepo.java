package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Item;

import javax.sql.DataSource;


@Repository
public class ItemRepo extends JdbcDaoSupport {

    @Autowired
    JdbcTemplate template;
    @Autowired
    DataSource dataSource;


    public ItemRepo(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Item getItem(String itemName) {
        String sql = "SELECT *  FROM snsto.items WHERE itemName=?";
        JdbcTemplate template = new JdbcTemplate(dataSource);


        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);
        Item item = template.queryForObject(sql, rm, itemName);



        return item;
    }




}