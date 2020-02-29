package com.framework.controller;

import com.framework.log.AppLogger;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class ControllerInitializer {
    public static void loadControllers() {
        AppLogger logger = AppLogger.getInstance(ControllerInitializer.class);
        logger.infoMessage("Scanning classpath for Controllers");

        Reflections reflections = new Reflections("example");
        for (Class<?> cl : reflections.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);
            System.out.println("Controller loaded -> Name: " + cl.getSimpleName() + " Mapping: " + controller.mapping());
            logger.infoMessage("Controller loaded -> Name: " + cl.getSimpleName() + " Mapping: " + controller.mapping());
        }
    }
}
