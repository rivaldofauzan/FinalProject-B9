package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUserSteps {

    private RequestSpecification request;
    private Response response;

    @Given("I have access to the User API")
    public void iHaveAccessToTheUserAPI() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        this.request = RestAssured.given().contentType("application/json");
    }

    @Given("I dont have an app-id in the header")
    public void iDontHaveAppIdInTheHeader() {
        this.request.header("app-id", "");
    }

    @Given("I have a valid app-id in the header {string}")
    public void iHaveAValidAppIdInTheHeader(String appID) {
        this.request.header("app-id", appID);
    }

    @Given("I have an invalid app-id in the header {string}")
    public void iHaveAnInvalidAppIdInTheHeader(String appID) {
        this.request.header("app-id", appID);
    }

    @When("I send a GET request to {string} with user ID {string}")
    public void iSendAGETRequestToWithUserID(String path, String userID) {
        response = this.request.get(path.replace("{id}", userID));
    }

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("the response should contain the user details")
    public void theResponseShouldContainTheUserDetails() {
        assertThat(response.getBody().asString(), not(emptyString()));
        assertThat(response.getBody().asString(), containsString("firstName")); 
    }

    @Then("the response should contain an error message about {string}")
    public void theResponseShouldContainAnErrorMessage(String errorMessage) {
        assertThat(response.getBody().asString(), containsString(errorMessage));
    }

    @Then("the response should contain multiple user details")
    public void theResponseShouldContainMultipleUserDetails() {
        assertThat(response.getBody().asString(), containsString("data"));
    }
}
