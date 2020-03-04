package com.framework.controller;

import java.util.Map;

public class MethodMap {
    private String methodName;
    private String methodURI;
    private String requestMethod;

    public MethodMap(String methodName, String methodURI, String requestMethod) {
        this.methodName = methodName;
        this.methodURI = methodURI;
        this.requestMethod = requestMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodURI() {
        return methodURI;
    }

    public void setMethodURI(String methodURI) {
        this.methodURI = methodURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
