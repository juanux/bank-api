package com.mybank.co.bank.actors.notifications;

public class TransferErrorNtf {

    private String message;

    public TransferErrorNtf(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
