package com.turing.niallbunting.steps;

import com.turing.niallbunting.services.ApiServices;
import com.turing.niallbunting.utils.Helpers;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

public class CommonStepDef {
    Logger logger=Logger.getLogger("CommonStepDef");
    private ApiServices apiServices;
    private Helpers helpers;
    private Response response;
    private static String id;

    public CommonStepDef(ApiServices apiServices, Helpers helpers) {
        this.apiServices = apiServices;
        this.helpers = helpers;
    }

    @Given("^I execute GET request on endpoint \"([^\"]*)\"$")
    public void i_execute_GET_request_on_endpoint(String endPoint) {
        if (endPoint.contains("{id}")) {
            this.response = apiServices.getService(endPoint.replace("{id}", id));
        } else {
            this.response = apiServices.getService(endPoint);
        }
    }

    @Given("^I execute POST request on endpoint \"([^\"]*)\" with payload \"([^\"]*)\"$")
    public void iExecutePOSTRequestOnEndpointWithPayload(String endPoint, String payLoad) {
        this.response = apiServices.postService(endPoint, helpers.readJsonFromFile(payLoad));
        id = response.then().extract().path("_id").toString();
    }

    @Then("^I should validate response as below$")
    public void i_should_validate_response_as_below(Map<String, String> expectedResponse) {
        for (Object o : expectedResponse.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = entry.getKey().toString();
            String actual = response.then().extract().path(key).toString();
            String expected = expectedResponse.get(key);
            assertThat(actual, is(expected));
        }
    }

    @Then("^I should expected status code as (\\d+)$")
    public void iShouldExpectedStatusCodeAs(int expectedStatus) {
        assertThat(expectedStatus, is(response.statusCode()));
    }

    @When("^I execute DELETE request on endpoint \"([^\"]*)\"$")
    public void iExecuteDELETERequestOnEndpoint(String endPoint) {
        String modifiedEndpoint = null;
        if (endPoint.contains("{id}")) {
            modifiedEndpoint = endPoint.replace("{id}", id);
        }
        this.response = apiServices.deleteService(modifiedEndpoint);
    }

    @Then("^I should expected response contains more than (\\d+) object$")
    public void iShouldExpectedResponseContainsMoreThanObject(int expected) {
        List<String> ids = response.then().extract().path("_id");
        assertThat(ids.size(), greaterThan(0));
    }

    @When("^I execute Patch request on endpoint \"([^\"]*)\"$")
    public void iExecutePatchRequestOnEndpoint(String endPoint) {
        if (endPoint.contains("{id}")) {
            this.response = apiServices.patchService(endPoint.replace("{id}", id));
        } else {
            this.response = apiServices.patchService(endPoint);
        }
    }

    @When("^I execute Patch request on endpoint \"([^\"]*)\" with payload \"([^\"]*)\"$")
    public void iExecutePatchRequestOnEndpointWithPayload(String endPoint, String payload){
        if (endPoint.contains("{id}")) {
            this.response = apiServices.patchService(endPoint.replace("{id}", id),helpers.readJsonFromFile(payload));
        } else {
            this.response = apiServices.patchService(endPoint,helpers.readJsonFromFile(payload));
        }
    }

    @Then("^I should expected response body returns \"([^\"]*)\"$")
    public void iShouldExpectedResponseBodyReturns(String expected) {
        assertThat(response.asString(),equalTo(expected));

    }
}
