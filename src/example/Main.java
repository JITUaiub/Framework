package example;

import com.framework.application.Application;
import com.framework.application.WebApplication;

public class Main {

    public static void main(String[] args) {
        Application application = new WebApplication("application-config.json");

        application.run();
    }
}
