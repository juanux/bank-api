package com.mybank.co;

import org.glassfish.jersey.server.ResourceConfig;

public class AppResourceConfig extends ResourceConfig {

    private AppBinder appBinder;

    public AppResourceConfig(AppBinder appBinder) throws Exception {
        this.appBinder = appBinder;
        register(this.appBinder);
        packages(true, "com.mybank.co.http");
    }
}
