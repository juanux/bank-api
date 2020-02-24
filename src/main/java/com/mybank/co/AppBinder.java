package com.mybank.co;

import akka.actor.ActorRef;
import com.mybank.co.bank.repositories.ITransactionsRepository;
import com.mybank.co.bank.service.IAccountService;
import org.glassfish.jersey.internal.inject.AbstractBinder;


public class AppBinder extends AbstractBinder {

    private IAccountService service;
    private ActorRef commandHandlerActor;
    private ITransactionsRepository transactionLogRepository;

    public AppBinder(IAccountService service,ITransactionsRepository transactionLogRepository, ActorRef commandHandlerActor) throws Exception {
        this.service = service;
        this.commandHandlerActor = commandHandlerActor;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Override
    protected void configure() {

        bind(service).to(IAccountService.class);
        bind(commandHandlerActor).to(ActorRef.class);
        bind(transactionLogRepository).to(ITransactionsRepository.class);
    }


}
