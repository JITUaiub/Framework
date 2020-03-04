package com.framework.server;

public class ServerFactory {
    public static Server getServer(String serverType) {
        switch (serverType) {
            case "tomcat":
                return new TomcatServer(8080);
            default:
                return null;
        }
    }
}
