package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> getAllAccounts(){
        String sql = "SELECT account_id, user_id, balance\n" +
                "FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Account> accountList = new ArrayList<>();
        while(results.next()){
            Account account = mapAccountFromResults(results);
            accountList.add(account);
        }
            return accountList;
    }
    public Account getAccountById(int account_id){
        String sql = "SELECT account_id, user_id, balance\n" +
                "FROM account\n" +
                "WHERE account_id = ?;";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);
        Account account = null;
        if(results.next()){
            account = this.mapAccountFromResults(results);
        }
        return account;
    }

    public boolean create(int user_id, BigDecimal balance){
        String sql = "INSERT INTO account(\n" +
                "\tuser_id, balance)\n" +
                "\tVALUES (?, ?);";
        Integer newAccountId;
        try{
            newAccountId = jdbcTemplate.queryForObject(sql, Integer.class, user_id, balance);
        }catch (DataAccessException e){
            return false;
        }
            return true;
    }

    private Account mapAccountFromResults(SqlRowSet results){
        return new Account(
                results.getBigDecimal("balance"),
                results.getInt("user_id"),
                results.getInt("account_id")
        );
    }

}