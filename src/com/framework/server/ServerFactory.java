package com.framework.server;

public class ServerFactory {
    public static Server getServer(Integer serverType) {
        switch (serverType) {
            case 1:
                return new TomcatServer(8080);
            default:
                return null;
        }
    }
}
