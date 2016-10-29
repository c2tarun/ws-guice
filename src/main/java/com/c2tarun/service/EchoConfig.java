package com.c2tarun.service;

import com.c2tarun.service.model.EchoModel;
import com.c2tarun.service.model.EchoModelInterface;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarun on 10/29/16.
 */
public class EchoConfig extends GuiceServletContextListener {
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(EchoController.class);

                bind(EchoModelInterface.class).to(EchoModel.class);

                Map<String, String> initParams = new HashMap<String, String>();
                initParams.put("com.sun.jersey.config.feature.Trace", "true");
                serve("/*").with(GuiceContainer.class, initParams);
            }
        });
    }
}
