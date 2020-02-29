package com.framework.parser;

public interface Parser {
    void parse (String filePath);
    Object getPropertyValue(String key);
    void removePropertyValue(String key);
    Boolean isEmpty ();
}
