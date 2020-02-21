package com.mybank.co.http.dto;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionListDTO implements DTO {

    private  ArrayList<TransferDTO> transactions;

    public TransactionListDTO(){}

    public TransactionListDTO(ArrayList<TransferDTO> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<TransferDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<TransferDTO> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionListDTO)) return false;
        TransactionListDTO that = (TransactionListDTO) o;
        return Objects.equals(getTransactions(), that.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactions());
    }
}
