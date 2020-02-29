package com.framework.server;

import java.util.HashMap;

public interface Server {
    void init (HashMap<String, String> servletList);
    void start ();
    void stop ();
    void destroy();
    void setPort(int port);
    int getPort();
    String getServerName();
    String getHost();
}
