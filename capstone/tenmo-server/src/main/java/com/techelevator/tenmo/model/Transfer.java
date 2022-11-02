package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private int transferId;
    private int fromAccountId;
    private int toAccountId;
    private BigDecimal transferBalance;

    public Transfer(int transferId, int fromAccountId, int toAccountId, BigDecimal transferBalance) {
        this.transferId = transferId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.transferBalance = transferBalance;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getTransferBalance() {
        return transferBalance;
    }

    public void setTransferBalance(BigDecimal transferBalance) {
        this.transferBalance = transferBalance;
    }
}
