package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public List<Item> getAllItems(){
        String sql ="SELECT * FROM sns.items";

        return this.template.query(sql, new ResultSetExtractor<List<Item>>() {
            @Override
            public List<Item> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id;
                String name;
                double unitPrice;
                int itemCode;

                ArrayList<Item> allItems = new ArrayList<>();

                while (rs.next()) {
                    id = rs.getInt("idItems");
                    name = rs.getString("name");
                    unitPrice = rs.getDouble("unitPrice");
                    itemCode = rs.getInt("itemCode");


                    allItems.add(new Item(id, name, unitPrice, itemCode));
                }
                return allItems;
            }
        });
    }

    public Item selectItem(int id) {
        String sql = "SELECT * FROM sns.items WHERE idItems=?";

        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);
        Item item = template.queryForObject(sql, rm, id);
        return item;

    }

    public List<Item> searchItem(String search) {

        String sql = "SELECT * FROM sns.items WHERE name=?";

        RowMapper<Item> rm = new BeanPropertyRowMapper<>(Item.class);

        List<Item> searched = template.query(sql, rm, search);

        return searched;
    }

    public void updateItem(Item item, int id) {


        String sql = "UPDATE sns.items " +
                "SET name=?, unitPrice=?, itemCode=?" +
                "WHERE idmovies =" + id;

        this.template.update(sql, item.getName(), item.getUnitPrice(), item.getItemCode());


    }




}