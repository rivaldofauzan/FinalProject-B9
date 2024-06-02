package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;

public class UpdateUserSteps {
    private RequestSpecification httpRequest;
    private Response response;
    private String baseUrl = "https://dummyapi.io/data/v1/";
    private String userId = "60d0fe4f5311236168a109fa";

    @Given("I have a user registered on the system")
    public void iHaveAUserRegisteredOnTheSystem() {
        RestAssured.baseURI = baseUrl;
        this.httpRequest = RestAssured.given().header("app-id", "665be3a3461fbb24df280141").contentType(ContentType.JSON);
    }

    @When("I update the user with the title to {string}")
    public void iUpdateTheUserWithTheTitleTo(String newTitle) {
        String requestBody = String.format("{\"title\": \"%s\"}", newTitle);
        response = httpRequest.body(requestBody).put("user/" + userId);
    }

    @When("I update the user with the firstName to {string}")
    public void iUpdateTheUserWithTheFirstNameTo(String newFirstName) {
        String requestBody = String.format("{\"firstName\": \"%s\"}", newFirstName);
        response = httpRequest.body(requestBody).put("user/" + userId);
    }

    @When("I update the user with the lastName to {string}")
    public void iUpdateTheUserWithTheLastNameTo(String newLastName) {
        String requestBody = String.format("{\"lastName\": \"%s\"}", newLastName);
        response = httpRequest.body(requestBody).put("user/" + userId);
    }

    @When("I update the user with the gender to {string}")
    public void iUpdateTheUserWithTheGenderTo(String newGender) {
        String requestBody = String.format("{\"gender\": \"%s\"}", newGender);
        response = httpRequest.body(requestBody).put("user/" + userId);
    }

    @When("I update the user with the dateOfBirth to {string}")
    public void iUpdateTheUserWithTheDateOfBirthTo(String newDateOfBirth) {
        String requestBody = String.format("{\"dateOfBirth\": \"%s\"}", newDateOfBirth);
        response = httpRequest.body(requestBody).put("user/" + userId);
    }

    @When("I update the user without setting app-id header")
    public void iUpdateTheUserWithoutSettingAppIdHeader() {
        RequestSpecification requestWithoutAppId = RestAssured.given().contentType(ContentType.JSON);
        String requestBody = "{\"title\": \"mr\", \"firstName\": \"Ann\", \"lastName\": \"Mason\", \"gender\": \"female\", \"email\": \"ann.mason@example.com\", \"dateOfBirth\": \"1959-09-26T07:05:56.725Z\", \"phone\": \"(385)-245-2517\", \"location\": {\"street\": \"2698, Paddock Way\", \"city\": \"Orange\"}}";
        response = requestWithoutAppId.body(requestBody).put(baseUrl + "user/" + userId);
    }

    @When("I update the user with invalid app-id {string}")
    public void iUpdateTheUserWithInvalidAppId(String appId) {
        RequestSpecification requestWithInvalidAppId = RestAssured.given().header("app-id", appId).contentType(ContentType.JSON);
        String requestBody = "{\"title\": \"mr\", \"firstName\": \"Ann\", \"lastName\": \"Mason\", \"gender\": \"female\", \"email\": \"ann.mason@example.com\", \"dateOfBirth\": \"1959-09-26T07:05:56.725Z\", \"phone\": \"(385)-245-2517\", \"location\": {\"street\": \"2698, Paddock Way\", \"city\": \"Orange\"}}";
        response = requestWithInvalidAppId.body(requestBody).put(baseUrl + "user/" + userId);
    }

    @Then("I should receive a success response with status code {int}")
    public void iShouldReceiveASuccessResponseWithStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the title in the response should reflect the update to {string}")
    public void theTitleInTheResponseShouldReflectTheUpdateTo(String expectedTitle) {
        assertEquals(expectedTitle, response.jsonPath().getString("title"));
    }

    @Then("the response should have error message {string}")
    public void theResponseShouldHaveErrorMessage(String expectedError) {
        assertEquals(expectedError, response.jsonPath().getString("error"));
    }
}
