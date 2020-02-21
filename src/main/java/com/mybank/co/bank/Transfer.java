package com.mybank.co.bank;

import java.time.LocalDateTime;

/**
 * This class represent a transfer between 2 accounts
 */
public class Transfer {
    Account from;
    Account to;
    Long amount;
    LocalDateTime dateTime;
    ETransactionStatus status;

    public Transfer(Account from, Account to, Long amount, LocalDateTime dateTime, ETransactionStatus status) {
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

/**
 * This enum represent the different status of a transfer
 */
enum ETransactionStatus{
    NEW,
    PROCESSING,
    READY,
    FAILED
}
