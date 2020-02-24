package com.mybank.co.bank.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.mybank.co.bank.Transfer;
import com.mybank.co.bank.actors.events.TransferExecutedEvt;
import com.mybank.co.bank.repositories.ITransactionsRepository;

/**
 * This Actor have the responsibility of react to successful executed transaction and update the view
 */
public class TransactionEventsActor extends AbstractActor {

    private ITransactionsRepository repository;

    public TransactionEventsActor(ITransactionsRepository service) {
        this.repository = service;
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder().match(TransferExecutedEvt.class,evt->{
            ActorRef sender = getSender();
            Transfer transfer = repository.saveTransaction(evt.getTransfer()).join();
            sender.tell(transfer,getSelf());

        }).build();
    }

    public static Props props(ITransactionsRepository repository) {
        return Props.create(TransactionEventsActor.class, repository);
    }
}
