package snsinternaltransfer.sns.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.repo.ItemRepo;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    ItemRepo itemRepo;

    @Override
    public List<Item> getAllItems(){
        return itemRepo.getAllItems();
    }

    @Override
    public Item selectItem(int id){
        return itemRepo.selectItem(id);
    }

    @Override
    public List<Item> searchItem(String search){
        return itemRepo.searchItem(search);
    }

    @Override
    public void updateItem(Item item, int id){
        itemRepo.updateItem(item, id);
    }

    @Override
    public void createItem(Item item){
        itemRepo.createItem(item);
    }

    @Override
    public void deleteItem(int id){
        itemRepo.deleteItem(id);
    }
}
