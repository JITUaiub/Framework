package com.framework.parser;

public class ParserFactory {
    public Parser getParser(Integer parserType) {
        switch (parserType) {
            case 1:
                return new JSONParser();
            case 2:
                return null;
            default:
                return null;
        }
    }
}
