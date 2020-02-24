package com.mybank.co.bank.actors.notifications;

/**
 * This class represent a error event in the execution of an transaction
 */
public class TransferErrorNtf {

    private String message;

    public TransferErrorNtf(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
