package com.framework.log;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class AppLogger {
    private static AppLogger instance = null;
    private static Logger logger;
    private static List<String> logLevels;
    private AppLogger(Class className) {
        // Preparing Log
        logLevels = new ArrayList<String>();
        logger = Logger.getLogger(className.getName());
        logger.setUseParentHandlers(false);

        // Formatting Log
        LogFormatter logFormatter = new LogFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.CONFIG);
        handler.setFormatter(logFormatter);
        logger.addHandler(handler);
    }

    public static void setLogLevels (List<String> logLevel) {
        logLevels = logLevel;
    }

    public static void setClass(Class className) {
        logger = Logger.getLogger(className.getName());
    }

    public static AppLogger getInstance (Class className) {
        if (instance == null)
            return new AppLogger(className);
        setClass(className);
        return instance;
    }

    public Logger getLogger () {
        return logger;
    }

    public void infoMessage (String message) {
        if (logLevels.contains(LogLevels.INFO.toString()))
            logger.info(message);
    }
    public void configMessage (String message) {
        if (logLevels.contains(LogLevels.CONFIG.toString())){
            logger.setLevel(Level.CONFIG);
            logger.config(message);
        }
    }
    public void warningMessage (String message) {
        if (logLevels.contains(LogLevels.WARNING.toString()))
            logger.warning(message);
    }
    public void developmentMessage (String message) {
        if (logLevels.contains(LogLevels.DEVELOPMENT.toString()))
            logger.info(message);
    }

}
