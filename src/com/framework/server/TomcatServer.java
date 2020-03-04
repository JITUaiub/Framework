package com.framework.server;

import com.framework.controller.ControllerMap;
import com.framework.servlet.ControllerDispatcher;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
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

//        servletList.forEach((key, value) -> {
//            Tomcat.addServlet(applicationContext, key, new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp)
//                        throws ServletException, IOException {
//
//                    Writer w = resp.getWriter();
//                    w.write("Embedded Tomcat servlet.\n" + key);
//                    w.flush();
//                    w.close();
//                }
//            });
//            applicationContext.addServletMapping("", "");
//        });
        servletList.forEach((key, value) -> {
            value.forEach((valueKey, valueValue) -> {
                System.out.println("Add controller: " + key + " " + valueKey);
                Tomcat.addServlet(applicationContext, key, new HttpServlet() {
                    @Override
                    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                        super.service(req, res);

                        PrintWriter printWriter = res.getWriter();
                        printWriter.println("Hello From Framework");
                        printWriter.println("Controller: " + valueValue.getClassName());
                        printWriter.println("request path: " + key + valueKey);
                    }
                });
            });
        });

        Tomcat.addServlet(applicationContext, "Embedded", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {

                Writer w = resp.getWriter();
                w.write("Embedded Tomcat servlet.\n");
                w.flush();
                w.close();
            }
        });

        applicationContext.addServletMapping("/*", "Embedded");
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
