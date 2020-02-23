package com.mybank.co;

import akka.actor.ActorRef;
import com.mybank.co.bank.service.IAccountService;
import com.mybank.co.http.TransactionEndpoint;
import org.glassfish.jersey.internal.inject.AbstractBinder;


public class AppBinder extends AbstractBinder {

    private IAccountService service;
    private ActorRef commandHandlerActor;

    public AppBinder(IAccountService service, ActorRef commandHandlerActor) throws Exception {
        this.service = service;
        this.commandHandlerActor = commandHandlerActor;
    }

    @Override
    protected void configure() {

        bind(service).to(IAccountService.class);
        bind(commandHandlerActor).to(ActorRef.class);
    }


}
