package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    List<Transfer> getAllTransfersById(int account_id);

//   void updateBalancesAfterTransfer(int fromAccountId, int toAccountId, BigDecimal transferBalance);

   String sendTransfer(int fromAccountId, int toAccountId, BigDecimal transferBalance);

    Transfer getTransferByTransferId(int transfer_id);
}
