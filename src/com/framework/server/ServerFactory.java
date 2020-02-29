package com.framework.server;

import com.framework.parser.JSONParser;
import org.apache.catalina.startup.Tomcat;

public class ServerFactory {
    public static Server getServer(String serverType) {
        switch (serverType) {
            case "tomcat" :
                return new TomcatServer(8080, null);
            default:
                return null;
        }
    }
}
