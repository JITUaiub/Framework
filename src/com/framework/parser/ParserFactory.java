package com.framework.parser;

public class ParserFactory {
    public Parser getParser (String parserType) {
        switch (parserType) {
            case "json" :
                return new JSONParser();
            case "xml" :
                return null;
            default:
                return null;
        }
    }
}
