package com.framework.parser;

import com.framework.log.AppLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONParser implements Parser {
    private AppLogger logger = AppLogger.getInstance(this.getClass());
    private JSONObject configContent;
    JSONObject object;

    public JSONParser (){
        configContent = null;
        object = null;
    }

    @Override
    public void parse(String configFilePath) {
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        logger.developmentMessage("Starting JSON Parsing.");
        try {
            this.configContent = (JSONObject) jsonParser.parse(new FileReader(configFilePath));
            logger.developmentMessage("Finished JSON Parsing.");
            logger.developmentMessage(configFilePath + " CONTENT -> " + configContent.toJSONString());
        }
        catch (FileNotFoundException ex) {
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
