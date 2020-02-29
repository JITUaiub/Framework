package example.abc;

import com.framework.controller.Controller;
import com.framework.controller.Mapping;

@Controller(mapping = "/test2")
public class TestController2 {

    @Mapping(mapping = "/hello", requestMethod = "get")
    public String test () {

        return "hello";
    }

    @Mapping(mapping = "/hello2", requestMethod = "get")
    public String test2 () {

        return "hello2";
    }
}
