package com.mybank.co.bank.actors.events;

import com.mybank.co.bank.Transfer;

public class TransferFailedEvt {

    private Transfer transfer;
    private String error;

    public TransferFailedEvt(Transfer transfer, String error) {
        this.transfer = transfer;
        this.error = error;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public String getError() {
        return error;
    }
}
