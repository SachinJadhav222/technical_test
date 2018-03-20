package com.turing.niallbunting;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;

public class Hooks {
    Logger logger=Logger.getLogger("Hooks");
    @Before
    public void setup(){
        RestAssured.baseURI = "http://turing.niallbunting.com";
        RestAssured.port = 3001;
        logger.info("Base URL and PORT is up and running");
    }
}
