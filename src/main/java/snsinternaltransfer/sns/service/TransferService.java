package snsinternaltransfer.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    TransferRepo transferRepo;

    public void sendItem(Transfer transfer){
        transferRepo.sendItem(transfer);
    }

    public List<Transfer> getAllTransfers(){
        return transferRepo.getAllTransfers();
    }

    public Transfer selectTranfer(int id){
        return transferRepo.selectTransfer(id);
    }

    public void updateTransfer(Transfer transfer, int id){
        transferRepo.updateTransfer(transfer, id);
    }

    public List<Transfer> searchTransferByDep(String from){
        return transferRepo.searchTransferByDep(from);
    }
}
