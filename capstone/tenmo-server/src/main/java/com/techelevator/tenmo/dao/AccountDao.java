package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> getAllAccounts();

    Account getAccountById(int account_id);

    boolean create(int user_id, BigDecimal transferBalance);

    void addToAccount(BigDecimal transferBalance, int id);

    void subtractFromAccount(BigDecimal transferBalance, int id);

    BigDecimal getBalance(int fromAccountId);
}
