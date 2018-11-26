package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Item;


@Repository
public class ItemRepo {

    @Autowired
    JdbcTemplate template;

    public Item getItem(String itemName) {
        String sql = "SELECT name, unitPrice, itemCode  FROM sns.items WHERE name=" + itemName;


        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);
        Item item = template.queryForObject(sql, rm);

        return item;
    }


}