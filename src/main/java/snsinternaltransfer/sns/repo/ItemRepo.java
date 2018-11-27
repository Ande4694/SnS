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
public class ItemRepo  {

    @Autowired
    JdbcTemplate template;


    public Item getItem(String itemName) {
        String sql = "SELECT *  FROM sns.items WHERE name=?";


        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);
        Item item = template.queryForObject(sql, rm, itemName);

        return item;
    }




}