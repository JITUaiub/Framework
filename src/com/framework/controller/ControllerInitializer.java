package com.framework.controller;

import com.framework.log.AppLogger;
import org.reflections.Reflections;

import java.lang.reflect.Method;

public class ControllerInitializer {
    public static void loadControllers(String basePackage) {
        AppLogger.infoMessage(ControllerInitializer.class, "Scanning classpath for Controllers");

        Reflections reflections = new Reflections(basePackage);
        for (Class<?> cl : reflections.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);
            AppLogger.developmentMessage(ControllerInitializer.class, "Controller loaded -> Name: " + cl.getName() + " Mapping: " + controller.mapping());

            Method [] methodList = cl.getMethods();
            for (Method method : methodList) {
                Mapping mapping = method.getAnnotation(Mapping.class);
                if (mapping != null && !mapping.mapping().isEmpty()) {
                    AppLogger.developmentMessage(ControllerInitializer.class, "Controller -> Method loaded -> Class: " + cl.getName() + " Name: " + method.getName() + " Mapping: " + mapping.mapping());
                }
            }
        }
        AppLogger.infoMessage(ControllerInitializer.class, "Controller loading finished.");
    }
}
