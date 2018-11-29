package snsinternaltransfer.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snsinternaltransfer.sns.models.Transfer;
import snsinternaltransfer.sns.repo.TransferRepo;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepo transferRepo;

    @Override
    public List<Transfer> getTransfersFrom(int dep){
        return transferRepo.getTransfersFrom(dep);
    }

    @Override
    public void sendItem(Transfer transfer){
        transferRepo.sendItem(transfer);
    }

    @Override
    public String getFromViaInt(int id){
        return transferRepo.getToFromViaInt(id);
    }

    @Override
    public void deleteSending(int id){
        transferRepo.deleteTransfer(id);
    }

    @Override
    public List<Transfer> getAllTransfers(){
        return transferRepo.getAllTransfers();
    }

    @Override
    public Transfer selectTranfer(int id){
        return transferRepo.selectTransfer(id);
    }

    @Override
    public void updateTransfer(Transfer transfer, int id){
        transferRepo.updateTransfer(transfer, id);
    }

    @Override
    public List<Transfer> searchTransferByDep(String from){
        return transferRepo.searchTransferByDep(from);
    }
}
