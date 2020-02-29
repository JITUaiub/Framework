package example;

import com.framework.controller.Controller;
import com.framework.controller.Mapping;

@Controller(mapping = "/test")
public class TestController {

    @Mapping(mapping = "/hello", requestMethod = "get")
    public String test () {

        return "hello";
    }
}
