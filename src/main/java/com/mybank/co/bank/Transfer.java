package com.mybank.co.bank;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represent a transfer between 2 accounts
 */
public class Transfer {
    String from;
    String to;
    Double amount;
    LocalDateTime dateTime;
    ETransactionStatus status;

    public Transfer(String from, String to, Double amount, LocalDateTime dateTime, ETransactionStatus status) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ETransactionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(getFrom(), transfer.getFrom()) &&
                Objects.equals(getTo(), transfer.getTo()) &&
                Objects.equals(getAmount(), transfer.getAmount()) &&
                Objects.equals(getDateTime(), transfer.getDateTime()) &&
                getStatus() == transfer.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getAmount(), getDateTime(), getStatus());
    }
}

