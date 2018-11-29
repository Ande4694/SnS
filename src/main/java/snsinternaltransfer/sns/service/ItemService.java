package snsinternaltransfer.sns.service;

import snsinternaltransfer.sns.models.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();
    Item selectItem(int id);
    List<Item> searchItem(String search);
    void updateItem(Item item, int id);
    void createItem(Item item);
    void deleteItem(int id);

}
