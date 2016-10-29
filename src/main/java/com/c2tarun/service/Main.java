package com.c2tarun.service;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by tarun on 10/29/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // HTTP connector
        ServerConnector http = new ServerConnector(server);
        http.setHost("0.0.0.0");
        http.setPort(8080);

        server.addConnector(http);

        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");

        contextHandler.addEventListener(new EchoConfig());
        contextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        server.start();
    }
}
