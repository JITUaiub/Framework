package com.framework.log;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class AppLogger {
    private static Logger logger;
    public static List<String> logLevels;

    private static void prepareLogger(Class className, Level level, LogFormatter logFormatter) {
        // Preparing Log
//        logLevels = new ArrayList<String>();
        LogManager.getLogManager().reset();
        logger = Logger.getLogger(className.getName());
        logger.setUseParentHandlers(false);

        // Formatting Log
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(level);
        handler.setFormatter(logFormatter);
        logger.addHandler(handler);
    }

    public static void setLogLevels (List<String> logLevel) {
        logLevels = logLevel;
    }

    public Logger getLogger () {
        return logger;
    }

    public static void infoMessage (Class className, String message) {
        prepareLogger(className, Level.INFO, new LogFormatter());
        if (logLevels != null && logLevels.contains(LogLevels.INFO.toString()))
            logger.info(message);
    }
    public static void configMessage (Class className, String message) {
        prepareLogger(className, Level.CONFIG, new LogFormatter());
        if (logLevels != null && logLevels.contains(LogLevels.CONFIG.toString())){
            logger.setLevel(Level.CONFIG);
            logger.config(message);
        }
    }
    public static void warningMessage (Class className, String message) {
        prepareLogger(className, Level.WARNING, new LogFormatter());
        if (logLevels != null && logLevels.contains(LogLevels.WARNING.toString()))
            logger.warning(message);
    }
    public static void developmentMessage (Class className, String message) {
        prepareLogger(className, Level.INFO, new LogFormatter());
        if (logLevels != null && logLevels.contains(LogLevels.DEVELOPMENT.toString()))
            logger.info(message);
    }

}
