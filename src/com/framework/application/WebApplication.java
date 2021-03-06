package com.framework.application;

import com.framework.controller.ApplicationContext;
import com.framework.log.AppLogger;
import com.framework.parser.JSONParser;
import com.framework.parser.Parser;
import com.framework.server.Server;
import com.framework.server.ServerFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebApplication implements Application {
    private final String APPLICATION_CONFIG_PATH;

    public WebApplication(String app_config) {
        this.APPLICATION_CONFIG_PATH = app_config;
    }

    @Override
    public void initialize() {
        // Load Application Startup Properties
        Parser parser = new JSONParser();
        parser.parse(APPLICATION_CONFIG_PATH);

        // Setting up Logger
        AppLogger.setLogLevels((List<String>) parser.getPropertyValue("log-level"));

        System.out.println("\n" +
                " ______ _____            __  __ ________          ______  _____  _  __\n" +
                "|  ____|  __ \\     /\\   |  \\/  |  ____\\ \\        / / __ \\|  __ \\| |/ /\n" +
                "| |__  | |__) |   /  \\  | \\  / | |__   \\ \\  /\\  / / |  | | |__) | ' / \n" +
                "|  __| |  _  /   / /\\ \\ | |\\/| |  __|   \\ \\/  \\/ /| |  | |  _  /|  <  \n" +
                "| |    | | \\ \\  / ____ \\| |  | | |____   \\  /\\  / | |__| | | \\ \\| . \\ \n" +
                "|_|    |_|  \\_\\/_/    \\_\\_|  |_|______|   \\/  \\/   \\____/|_|  \\_\\_|\\_\\ [Version: " + parser.getPropertyValue("version") + "]\n");
        AppLogger.infoMessage(WebApplication.class, "Starting Web Application.");
        AppLogger.configMessage(WebApplication.class, "application-config.json Loaded into Application.");

        // APPLICATION INFO LOG
        AppLogger.infoMessage(WebApplication.class, "APPLICATION NAME -> " + parser.getPropertyValue("application-name"));
        AppLogger.infoMessage(WebApplication.class, "APPLICATION VERSION -> " + parser.getPropertyValue("version"));
        AppLogger.infoMessage(WebApplication.class, "Preferred Config Type -> " + parser.getPropertyValue("config-file-type"));

        // Load Controllers
        ApplicationContext.loadControllers(parser.getPropertyValue("controller-base-package").toString());


        // Start Server
        Server server = ServerFactory.getServer(Integer.valueOf((String) parser.getPropertyValue("server")));
        server.setPort(Integer.parseInt(parser.getPropertyValue("server-port").toString()));
        server.init(ApplicationContext.CONTROLLER_CONTEXT);

        AppLogger.infoMessage(WebApplication.class, server.getServerName() + " Server Starting On Port -> http://" + server.getHost() + ":" + server.getPort());
        server.start();
    }

    @Override
    public void run() {
        initialize();
    }

}
