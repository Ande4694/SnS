package snsinternaltransfer.sns.service;

import snsinternaltransfer.sns.models.Transfer;

import java.util.List;

public interface TransferService {

    void sendItem(Transfer transfer);
    void deleteSending(int id);
    List<Transfer> getAllTransfers();
    Transfer selectTranfer(int id);
    void updateTransfer(Transfer transfer, int id);
    List<Transfer> searchTransferByDep(String from);
}
