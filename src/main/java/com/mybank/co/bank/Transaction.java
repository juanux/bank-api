package com.mybank.co.bank;

import java.time.LocalDateTime;

public class Transaction {
    Account from;
    Account to;
    Long amount;
    LocalDateTime dateTime;
    ETransactionStatus status;

    public Transaction(Account from, Account to, Long amount, LocalDateTime dateTime, ETransactionStatus status) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ETransactionStatus getStatus() {
        return status;
    }
}


enum ETransactionStatus{
    PROCESSING,
    READY,
    FAILED
}
