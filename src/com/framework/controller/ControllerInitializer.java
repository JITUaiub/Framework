package com.framework.controller;

import com.framework.log.AppLogger;
import org.reflections.Reflections;

public class ControllerInitializer {
    public static void loadControllers() {
        AppLogger.infoMessage(ControllerInitializer.class, "Scanning classpath for Controllers");

        Reflections reflections = new Reflections("example");
        for (Class<?> cl : reflections.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);
            AppLogger.infoMessage(ControllerInitializer.class, "Controller loaded -> Name: " + cl.getName() + " Mapping: " + controller.mapping());
        }
        AppLogger.infoMessage(ControllerInitializer.class, "Controller loading finished.");
    }
}
