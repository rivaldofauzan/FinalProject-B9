package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteUserSteps {

    private RequestSpecification request;
    private Response response;

    @Given("I have access to the API User Controller")
    public void iHaveAccessToTheUserAPI() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        this.request = RestAssured.given().contentType("application/json");
    }

    @Given("I have a valid app-id {string}")
    public void iHaveAValidAppId(String appID) {
        this.request.header("app-id", appID);
    }

    @Given("I don't have an app-id")
    public void iDontHaveAnAppId() {
        this.request.header("app-id", "");
    }

    @When("I send a DELETE request to {string} with user ID {string}")
    public void iSendADELETERequestToWithUserID(String path, String userID) {
        response = this.request.delete(path.replace("{id}", userID));
    }

    @Then("I should receive success with status code {int}")
    public void iShouldReceiveSuccessWithStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("the response should display the user id {string}")
    public void theResponseShouldDisplayTheUserId(String userID) {
        assertThat(response.getBody().asString(), containsString(userID));
    }

    @Then("I should receive an error with status code {int}")
    public void iShouldReceiveAnErrorWithStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("the response should display an error {string}")
    public void theResponseShouldDisplayAnError(String errorMessage) {
        assertThat(response.getBody().asString(), containsString(errorMessage));
    }
}
