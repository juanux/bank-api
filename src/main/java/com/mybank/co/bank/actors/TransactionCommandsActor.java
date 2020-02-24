package com.mybank.co.bank.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.mybank.co.bank.Account;
import com.mybank.co.bank.ETransactionStatus;
import com.mybank.co.bank.Transfer;
import com.mybank.co.bank.actors.commands.ExecuteTransferCmd;
import com.mybank.co.bank.actors.events.TransferExecutedEvt;
import com.mybank.co.bank.actors.notifications.TransferErrorNtf;
import com.mybank.co.bank.service.IAccountService;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionCommandsActor extends AbstractActor {

    private ActorRef eventsActor;
    private IAccountService service;

    public TransactionCommandsActor(ActorRef eventsActor, IAccountService service) {
        this.eventsActor = eventsActor;
        this.service = service;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ExecuteTransferCmd.class,evt->{
            ActorRef sender = getSender();

            Optional<Account> sourceAccountMaybe = service.getByAccountId(evt.getSourceAccount());
            Optional<Account> targetSourceAccountMaybe = service.getByAccountId(evt.getTargetaccount());

            if(sourceAccountMaybe.isPresent() && targetSourceAccountMaybe.isPresent()){
                Account sourceAccount = sourceAccountMaybe.get();
                Account targetAccount = targetSourceAccountMaybe.get();

                if(sourceAccount.getBalance() >= evt.getAmount()){
                    if(sourceAccount.getCurrency() == targetAccount.getCurrency()){

                        service.transfer(sourceAccount,targetAccount,evt.getAmount());
                        Transfer transfer = new Transfer(sourceAccount.getAccountNumber(), targetAccount.getAccountNumber(),evt.getAmount(), LocalDateTime.now(), ETransactionStatus.READY);
                        eventsActor.tell(new TransferExecutedEvt(transfer),sender);

                    }else{
                        sender.tell(new TransferErrorNtf("Incompatible currencies " + sourceAccount.getCurrency() + " - " + targetAccount.getCurrency()), getSelf());
                    }

                }else{
                    sender.tell(new TransferErrorNtf("Not founds enough to make the transfer."), getSelf());
                }

            }else{
                sender.tell(new TransferErrorNtf("Account don't exist"), getSelf());
            }

        }).build();
    }

    public static Props props(ActorRef eventsActor, IAccountService service) {
        return Props.create(TransactionCommandsActor.class, eventsActor,service);
    }

}
