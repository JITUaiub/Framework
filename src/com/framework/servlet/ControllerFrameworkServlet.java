package com.framework.servlet;

import com.framework.controller.ApplicationContext;
import com.framework.controller.ControllerMap;
import com.framework.controller.FrameworkServlet;
import com.framework.log.AppLogger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerFrameworkServlet implements FrameworkServlet {
    private String controllerName;
    private String path;

    public ControllerFrameworkServlet(String controllerName, String path) {
        this.controllerName = controllerName;
        this.path = path;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ControllerMap controllerMap = ApplicationContext.CONTROLLER_CONTEXT.get(controllerName).get(path);

        Class controllerClass = null;
        Method [] methodsList = null;
        try {
            controllerClass = Class.forName(controllerMap.getQualifiedClassName());
            methodsList = controllerClass.getMethods();
            for (Method method : methodsList) {
                if (method.getName().equals(controllerMap.getMethods().get(path).getMethodName())) {
                    String result = (String) method.invoke(controllerClass.newInstance());
                    System.out.println("Result: " + result);
                }
            }

        } catch (ClassNotFoundException e) {
            AppLogger.warningMessage(ControllerFrameworkServlet.class, controllerMap.getQualifiedClassName() + "class not found.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Writer w = resp.getWriter();
        w.write("Welcome from Framework.\n");
        w.write("Controller Name: " + this.controllerName);
        w.write("\nPath: " + this.path);
        w.write("\nLoad Class: " + controllerMap.getClassName());
        w.write("\nLoad Method: " + controllerMap.getMethods().get(path).getMethodName());
        w.flush();
        w.close();
    }
}
