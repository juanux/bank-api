package com.mybank.co.bank.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.mybank.co.bank.service.IAccountService;

public class TransactionEventsActor extends AbstractActor {

    private IAccountService service;

    public TransactionEventsActor(IAccountService service) {
        this.service = service;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }


    public static Props props(IAccountService service) {
        return Props.create(TransactionEventsActor.class, service);
    }
}
