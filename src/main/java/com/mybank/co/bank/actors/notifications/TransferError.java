package com.mybank.co.bank.actors.notifications;

public class TransferError {

    private String message;

    public TransferError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
