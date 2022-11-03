package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao=transferDao;
    }

    @PostMapping("")
    public Transfer updateBalancesAfterTransfer(@RequestParam BigDecimal transferBalance, int fromAccountId, int toAccountId) {
        return this.transferDao.updateBalancesAfterTransfer(transferBalance, fromAccountId, toAccountId);
    }

}
