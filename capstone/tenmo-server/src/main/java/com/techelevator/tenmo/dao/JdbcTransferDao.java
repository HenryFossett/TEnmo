package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Transfer> getAllTransfersById(int account_id){
        String sql = "SELECT transfer_id, from_account_id, to_account_id, transfer_balance\n" +
                "FROM transfer\n" +
                "JOIN account ON transfer.from_account_id = account.account_id\n" +
                "WHERE account_id = ?;";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, account_id);
        List<Transfer> transferList = new ArrayList<>();
        while(results.next()){
            Transfer t = mapTransferFromResults(results);
            transferList.add(t);
        }
        return transferList;
    }
    public Transfer getTransferByTransferId(int transfer_id){
        String sql = "SELECT from_account_id, to_account_id, transfer_balance\n"+
                "FROM transfer\n" +
                "WHERE transfer_id = ?;";
        Transfer transfer = null;
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, transfer_id);
        if(results.next()){
            transfer = mapTransferFromResults(results);
        }
        return transfer;
    }

//    @Override
//    public void updateBalancesAfterTransfer(int fromAccountId, int toAccountId, BigDecimal transferBalance) {
//            String sql = "INSERT INTO transfer (from_account_id, to_account_id, transfer_balance)\n" +
//                    "VALUES (?, ?, ?);";
//           jdbcTemplate.update(sql, fromAccountId, toAccountId, transferBalance);
//           String sql2 = "UPDATE account SET transfer_balance = transfer_balance - ? WHERE account_id = ?;";
//           jdbcTemplate.update(sql2,transferBalance, fromAccountId);
//           String sql3 ="UPDATE account SET transfer_balance = transfer_balance + ? WHERE account_id = ?;";
//           jdbcTemplate.update(sql3,transferBalance, toAccountId);
//        }


   public String sendTransfer(int fromAccountId, int toAccountId, BigDecimal transferBalance){
        String sql = "INSERT INTO transfer (from_account_id, to_account_id, transfer_balance)\n" +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, fromAccountId, toAccountId, transferBalance);
        return "Transfer Complete";
   }


    private Transfer mapTransferFromResults(SqlRowSet results){
        return new Transfer(
                results.getInt("transfer_id"),
                results.getInt("from_account_id"),
                results.getInt("to_account_id"),
                results.getBigDecimal("balance")
        );
    }
}
