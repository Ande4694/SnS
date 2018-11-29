package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Item;

import java.util.List;

@Repository
public interface ItemRepo {

    Item getItem(String itemName);
    List<Item> getAllItems();
    Item selectItem(int id);
    List<Item> searchItem(String search);
    void updateItem(Item item, int id);
    void createItem(Item item);
    void deleteItem(int id);

}
