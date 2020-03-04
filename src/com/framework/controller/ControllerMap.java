package com.framework.controller;

import java.util.Map;

public class ControllerMap {
    private String className;
    private String qualifiedClassName;
    private String classURI;
    private Map<String, MethodMap> methods;

    public ControllerMap(String className, String qualifiedClassName, String classURI, Map<String, MethodMap> methods) {
        this.className = className;
        this.qualifiedClassName = qualifiedClassName;
        this.classURI = classURI;
        this.methods = methods;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    public String getClassURI() {
        return classURI;
    }

    public void setClassURI(String classURI) {
        this.classURI = classURI;
    }

    public Map<String, MethodMap> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, MethodMap> methods) {
        this.methods = methods;
    }
}
