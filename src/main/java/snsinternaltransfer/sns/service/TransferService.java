package snsinternaltransfer.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;

@Service
public class TransferService {

    @Autowired
    TransferRepo transferRepo;

    public void sendItem(Transfer transfer){
        transferRepo.sendItem(transfer);
    }
}
