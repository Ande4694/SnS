package snsinternaltransfer.sns.repo;

import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Transfer;

import java.util.List;

@Repository
public interface TransferRepo {

    String getToFromViaInt(int id);
    int getToFromViaName(String name);
    void sendItem(Transfer transfer);
    List<Transfer> getAllTransfers();
    Transfer selectTransfer(int id);
    void updateTransfer(Transfer transfer, int id);
    List<Transfer> searchTransferByDep(String from);
    void deleteTransfer(int id);
    int getToViaUsername(String username);
    List<Transfer> getTransfersFrom(int dep);


}
