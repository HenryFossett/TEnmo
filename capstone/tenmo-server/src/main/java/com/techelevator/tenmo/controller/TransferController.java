package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;


    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao) {
        this.transferDao=transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers", method = RequestMethod.POST)
    public void updateBalancesAfterTransfer(@RequestParam(required = true) int toAccountId,
                                            @RequestParam(required = true)BigDecimal transferBalance,
                                            Principal principal) {
        User u = userDao.findByUsername(principal.getName());
        Account a = accountDao.getAccountById(u.getId());
        int fromAccountId = a.getAccountId();
        transferDao.updateBalancesAfterTransfer(fromAccountId, toAccountId, transferBalance);
    }

}
