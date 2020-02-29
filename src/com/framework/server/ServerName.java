package com.framework.server;

public enum ServerName {
    TOMCAT("tomcat");

    private final String text;

    ServerName(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
