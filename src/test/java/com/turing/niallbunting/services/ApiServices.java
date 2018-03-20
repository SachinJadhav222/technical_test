package com.turing.niallbunting.services;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiServices {
    Logger logger=Logger.getLogger("ApiServices");
    public Response getService(String endpoint) {
        logger.info("GET Request Executed");
        return given()
                .contentType("application/json")
                .when()
                .get(endpoint);
    }


    public Response postService(String endpoint, JsonObject payLoad) {
        logger.info("POST Request Executed");
        return given()
                .contentType("application/json")
                .when()
                .body(payLoad)
                .post(endpoint);
    }

    public Response deleteService(String endpoint) {
        logger.info("DELETE Request Executed");
        return given()
                .contentType("application/json")
                .when()
                .delete(endpoint);
    }

    public Response patchService(String endpoint) {
        logger.info("PATCH Request Executed");
        return given()
                .contentType("application/json")
                .when()
                .patch(endpoint);
    }

    public Response patchService(String endpoint,JsonObject payload) {
        logger.info("PATCH Request Executed");
        return given()
                .contentType("application/json")
                .body(payload)
                .when()
                .patch(endpoint);
    }
}
