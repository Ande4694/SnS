package snsinternaltransfer.sns.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import snsinternaltransfer.sns.models.Department;
import snsinternaltransfer.sns.models.Item;
import snsinternaltransfer.sns.models.Transfer;

import java.sql.SQLException;

@Repository
public interface TransferRepo {


/*    int getSenderDepartment();*/

    void sendItem(Transfer transfer) throws SQLException;
    //indsæt i db

    Item getItem(String itemName)throws SQLException;
    // henter feks. laks

    Department getDepartment(int departmentId)throws SQLException;
}
