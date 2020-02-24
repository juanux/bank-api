package com.mybank.co.bank.actors.commands;

/**
 * This class represent a command to execute a new transfer
 */
public class ExecuteTransferCmd {

    private String sourceAccount;
    private String targetaccount;
    private Double amount;

    public ExecuteTransferCmd(String sourceAccount, String targetaccount, Double amount) {
        this.sourceAccount = sourceAccount;
        this.targetaccount = targetaccount;
        this.amount = amount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getTargetaccount() {
        return targetaccount;
    }

    public Double getAmount() {
        return amount;
    }
}
