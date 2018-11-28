package snsinternaltransfer.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;

@Service
public class TransferService {

    @Autowired
    TransferRepo transferRepo;

    public Transfer sendItem(String to, String from, String date, String item, String sender, double amount){
        return transferRepo.sendItem(to, from, date, item, sender, amount);
    }
}
