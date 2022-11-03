package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;
    }
    @GetMapping(path ="")
    public List<Account> getAllAccounts(){
        return this.accountDao.getAllAccounts();
    }

    @GetMapping(path ="/{id}")
    public Account getAccountById(@PathVariable(name="id")  int account_id) {
        return this.accountDao.getAccountById(account_id);
    }
    @PostMapping("")
    public boolean create(@RequestParam int user_id, @RequestParam BigDecimal balance){
        return this.accountDao.create(user_id, balance);
    }


}
