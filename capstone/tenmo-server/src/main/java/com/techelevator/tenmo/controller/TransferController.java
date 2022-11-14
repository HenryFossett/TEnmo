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
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;
    //private BigDecimal fromBalance = BigDecimal.valueOf(accountDao.getBalance(2001));



    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao) {
        this.transferDao=transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

//    @PreAuthorize("permitAll")
//    @RequestMapping(path = "/transfers", method = RequestMethod.POST)
//    public void updateBalancesAfterTransfer(@RequestParam(required = true) int toAccountId,
//                                            @RequestParam(required = true)BigDecimal transferBalance,
//                                            Principal principal) {
//        User u = userDao.findByUsername(principal.getName());
//        Account a = accountDao.getAccountById(u.getId());
//        int fromAccountId = a.getAccountId();
//        transferDao.updateBalancesAfterTransfer(fromAccountId, toAccountId, transferBalance);
//    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers", method = RequestMethod.POST)
    public String sendTransfer(@RequestParam int fromAccountId,
                               @RequestParam int toAccountId,
                               @RequestParam BigDecimal transferBalance){
        if(fromAccountId == toAccountId){
            return "You cannot send money to yourself";
        }
        if(transferBalance.compareTo(BigDecimal.ZERO) != 1){
            return "You cannot send zero or less than zero dollars";
        }
        if(transferBalance.compareTo(accountDao.getBalance(fromAccountId)) == 1){
            return "Insufficient funds";
        }
        accountDao.addToAccount(transferBalance, toAccountId);
        accountDao.subtractFromAccount(transferBalance, fromAccountId);
        transferDao.sendTransfer(fromAccountId, toAccountId, transferBalance);
        return "Transaction complete!";
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers/{fromAccountId}", method = RequestMethod.GET)
    public List<Transfer> getAllTransfersById(@RequestParam int fromAccountId){
        return this.transferDao.getAllTransfersById(fromAccountId);
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "transfers/{transfer_id}", method = RequestMethod.GET)
    public Transfer getTransferByTransferId(@RequestParam int transfer_id){
        return this.transferDao.getTransferByTransferId(transfer_id);
    }
}
