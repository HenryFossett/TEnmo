package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    List<Transfer> getAllTransfersById(int account_id);

   Transfer updateBalancesAfterTransfer(BigDecimal transferBalance, int fromAccountId, int toAccountId);
}
