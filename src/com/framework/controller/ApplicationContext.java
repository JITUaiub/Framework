package com.framework.controller;

import com.framework.log.AppLogger;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplicationContext {
    String CLASS_NAME_SEPARATOR_REZEX = "\\.";
    Map<String, Map<String, ControllerMap>> CONTROLLER_CONTEXT = new HashMap<>();

    static void loadControllers(String basePackage) {
        AppLogger.infoMessage(ApplicationContext.class, "Scanning classpath for Controllers");

        Reflections reflections = new Reflections(basePackage);
        for (Class<?> cl : reflections.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);
            AppLogger.developmentMessage(ApplicationContext.class, "Controller loaded -> Name: " + cl.getName() + " Mapping: " + controller.mapping());

            String className = cl.getName().split(CLASS_NAME_SEPARATOR_REZEX)[cl.getName().split(CLASS_NAME_SEPARATOR_REZEX).length - 1];
            String qualifiedClassName = cl.getName();
            String classURI = controller.mapping();
            Map<String, MethodMap> methodsWithURI = new HashMap<>();

            Map<String, ControllerMap> tmpControllerMap = new HashMap<>();
            Method [] methodList = cl.getMethods();
            for (Method method : methodList) {
                Mapping mapping = method.getAnnotation(Mapping.class);
                if (mapping != null && !mapping.uri().isEmpty()) {
                    methodsWithURI.put(mapping.uri(), new MethodMap(method.getName(), mapping.uri(), mapping.requestMethod()));

                    tmpControllerMap.put(mapping.uri(), new ControllerMap(className, qualifiedClassName, classURI, methodsWithURI));


                    AppLogger.developmentMessage(ApplicationContext.class, "Controller -> Method loaded -> Class: " + cl.getName() + " Name: " + method.getName() + " Mapping: " + mapping.uri());
                }
            }
            CONTROLLER_CONTEXT.put(controller.mapping(), tmpControllerMap);
        }
        AppLogger.infoMessage(ApplicationContext.class, "Controllers added to Application context.");
    }
}
