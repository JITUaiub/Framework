package com.framework.controller;

import com.framework.log.AppLogger;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface ApplicationContext {
    String CLASS_NAME_SEPARATOR = "\\.";
    Map<String, Map<String, ControllerMap>> CONTROLLER_CONTEXT = new HashMap<>();

    static void loadControllers(String basePackage) {
        AppLogger.infoMessage(ApplicationContext.class, "Scanning classpath for Controllers");

        Reflections reflections = new Reflections(basePackage);
        for (Class<?> cl : reflections.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);

            String className = cl.getName().split(CLASS_NAME_SEPARATOR)[cl.getName().split(CLASS_NAME_SEPARATOR).length - 1];
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
                }
            }
            CONTROLLER_CONTEXT.put(controller.mapping(), tmpControllerMap);
        }
        AppLogger.infoMessage(ApplicationContext.class, "Controllers loaded into Application context.");
    }
}
