package com.mybank.co;

import com.mybank.co.bank.service.IAccountService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class AppBinder extends AbstractBinder {

    private IAccountService service;

    public AppBinder(IAccountService service) throws Exception {
        this.service = service;
    }

    @Override
    protected void configure() {
        bind(service).to(IAccountService.class);
    }


}
