package com.framework.log;

public enum LogLevels {
    INFO("info"),
    DEVELOPMENT("development"),
    WARNING("warning"),
    CONFIG("config")
    ;

    private final String text;

    LogLevels(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
