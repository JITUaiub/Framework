package com.framework.parser;

import com.framework.log.AppLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONParser implements Parser {
    private JSONObject configContent;
    JSONObject object;

    public JSONParser() {
        configContent = null;
        object = null;
    }

    @Override
    public void parse(String configFilePath) {
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        AppLogger.developmentMessage(JSONParser.class, "Starting JSON Parsing.");
        try {
            this.configContent = (JSONObject) jsonParser.parse(new FileReader(configFilePath));
            AppLogger.developmentMessage(JSONParser.class, "Finished JSON Parsing.");
            AppLogger.developmentMessage(JSONParser.class, configFilePath + " CONTENT -> " + configContent.toJSONString());
        } catch (FileNotFoundException ex) {
//            throw ex;
        } catch (ParseException ex) {
//            throw ex;
        } catch (IOException ex) {
//            throw ex;
        }
    }

    @Override
    public Object getPropertyValue(String key) {
        return configContent.get(key);
    }

    @Override
    public void removePropertyValue(String key) {
        configContent.remove(key);
    }

    @Override
    public Boolean isEmpty() {
        return configContent.isEmpty();
    }
}
