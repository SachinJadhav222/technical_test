package com.turing.niallbunting;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;

public class Hooks {
    Logger logger=Logger.getLogger("Hooks");
    @Before
    public void setup(Scenario scenario){
        RestAssured.baseURI = "http://turing.niallbunting.com";
        RestAssured.port = 3001;
        logger.info(scenario.getName());
        logger.info("Base URL and PORT is up and running");
    }
    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            logger.info("Scenario Failed ");
        } else {
            logger.info("Scenario Passed ");
        }

    }
}
