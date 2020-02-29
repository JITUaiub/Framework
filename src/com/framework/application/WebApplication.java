package com.framework.application;

import com.framework.controller.ControllerInitializer;
import com.framework.log.AppLogger;
import com.framework.parser.JSONParser;
import com.framework.parser.Parser;
import com.framework.server.Server;
import com.framework.server.ServerFactory;

import java.util.List;

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
        AppLogger.infoMessage(WebApplication.class, "Starting Web Application.");
        AppLogger.configMessage(WebApplication.class, "application-config.json Loaded into Application.");

        // APPLICATION INFO LOG
        AppLogger.infoMessage(WebApplication.class, "APPLICATION NAME -> " + parser.getPropertyValue("application-name"));
        AppLogger.infoMessage(WebApplication.class, "APPLICATION VERSION -> " + parser.getPropertyValue("version"));
        AppLogger.infoMessage(WebApplication.class, "Preferred Config Type -> " + parser.getPropertyValue("config-file-type"));

        // Load Controllers
        ControllerInitializer.loadControllers();


        // Start Server
        Server server = ServerFactory.getServer(parser.getPropertyValue("server").toString());
        server.setPort(Integer.parseInt(parser.getPropertyValue("server-port").toString()));
        AppLogger.infoMessage(WebApplication.class, server.getServerName() + " Server Starting On Port -> http://" + server.getHost() + ":" + server.getPort());
        server.start();
    }

    @Override
    public void run() {
        initialize();
    }

}
