package com.turing.niallbunting.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Helpers {
  Logger logger =Logger.getLogger("Helpers");
    public JsonObject readJsonFromFile(String fileName) {
        JsonObject jsonObject = new JsonObject();
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/payloads/" + fileName + ".json"));
            jsonObject = jsonElement.getAsJsonObject();
            logger.info("Reading data from PayLoad in JSON format and returning the JSON Object");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("Unable to Read PayLoad");
        }

        return jsonObject;
    }
}
