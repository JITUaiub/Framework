package com.framework.server;

import com.framework.controller.ApplicationConfig;
import com.framework.controller.ControllerMap;
import com.framework.log.AppLogger;
import com.framework.servlet.ControllerFrameworkServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TomcatServer implements Server {
    private Tomcat tomcat;
    private int port;
    private Context applicationContext;

    public TomcatServer(int port) {
        this.tomcat = new Tomcat();
        this.tomcat.setPort(port);
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void init(Map<String, Map<String, ControllerMap>> servletList) {
        applicationContext = tomcat.addContext("", new File(".").getAbsolutePath());

        servletList.forEach((classMapping, classValues) -> {
            classValues.forEach((methodMapping, methodValues) -> {
                String controllerPath = classMapping.concat(methodMapping).toLowerCase();
                Tomcat.addServlet(applicationContext, controllerPath, new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp)
                                throws IOException {
                            ControllerFrameworkServlet controllerDispatcher = new ControllerFrameworkServlet(classMapping, methodMapping);
                            controllerDispatcher.service(req, resp);
                        }
                });
                applicationContext.addServletMapping(controllerPath, controllerPath);
                AppLogger.developmentMessage(TomcatServer.class, "Controller loaded -> Name: " + methodValues.getClassName() + " Mapping: " + controllerPath);
            });
        });

        if (ApplicationConfig.ALLOW_UNMAPPED_URL_TO_BLANK) {

        }
    }

    private Tomcat getTomcat() {
        return tomcat;
    }

    @Override
    public void start() {
        try {
            getTomcat().start();
            getTomcat().getServer().await();
        } catch (LifecycleException ex) {

        }
    }

    @Override
    public void stop() {
        try {
            getTomcat().stop();
        } catch (LifecycleException ex) {

        }
    }

    @Override
    public void destroy() {
        try {
            getTomcat().destroy();
        } catch (LifecycleException ex) {

        }
    }

    @Override
    public void setPort(int port) {
        this.port = port;
        this.getTomcat().setPort(this.port);
    }

    @Override
    public String getServerName() {
        return ServerName.TOMCAT.toString().toUpperCase();
    }

    @Override
    public String getHost() {
        return this.getTomcat().getHost().getName();
    }

}
