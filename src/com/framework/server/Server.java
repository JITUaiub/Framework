package com.framework.server;

import com.framework.controller.ControllerMap;

import java.util.Map;

public interface Server {
    void init(Map<String, Map<String, ControllerMap>> servletList);

    void start();

    void stop();

    void destroy();

    void setPort(int port);

    int getPort();

    String getServerName();

    String getHost();
}
