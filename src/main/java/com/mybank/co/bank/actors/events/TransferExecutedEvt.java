package com.mybank.co.bank.actors.events;

import com.mybank.co.bank.Transfer;

public class TransferExecutedEvt {
    private Transfer transfer;

    public TransferExecutedEvt(Transfer transfer) {
        this.transfer = transfer;
    }

    public Transfer getTransfer() {
        return transfer;
    }
}
