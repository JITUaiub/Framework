package com.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrameworkServlet {
    void service(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
