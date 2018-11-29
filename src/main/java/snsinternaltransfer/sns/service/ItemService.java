package snsinternaltransfer.sns.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.repo.ItemRepo;

import java.util.List;

@Service
public class ItemService {


    @Autowired
    ItemRepo itemRepo;

    public List<Item> getAllItems(){
        return itemRepo.getAllItems();
    }

    public Item selectItem(int id){
        return itemRepo.selectItem(id);
    }

    public List<Item> searchItem(String search){
        return itemRepo.searchItem(search);
    }

    public void updateItem(Item item, int id){
        itemRepo.updateItem(item, id);
    }

    public void createItem(Item item){
        itemRepo.createItem(item);
    }

    public void deleteItem(int id){
        itemRepo.deleteItem(id);
    }
}
