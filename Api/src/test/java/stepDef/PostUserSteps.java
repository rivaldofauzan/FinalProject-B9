package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostUserSteps {

    private RequestSpecification httpRequest;
    private Response response;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;

    @Given("I have access to the User API Controller")
    public void iHaveAccessToTheUserAPI() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
        this.httpRequest = RestAssured.given().contentType("application/json");
    }

    @Given("I am authorized with a valid app-id {string}")
    public void iHaveAValidAppId(String appID) {
        this.httpRequest.header("app-id", appID);
    }

    @Given("I have a user payload with title {string} and other required fields")
    public void iHaveAUserPayloadWithTitleAndOtherRequiredFields(String title) {
        this.title = title;
        this.firstName = "Alex";
        this.lastName = "Gonzales";
        this.picture = "http://example.com/picture.jpg";
        this.gender = "male";
        this.email = "alex.gonzales34563@example.com";
        this.dateOfBirth = "1990-01-01";
        this.phone = "123456789";
        this.street = "123 Main St";
        this.city = "New York";
        this.state = "NY";
        this.country = "USA";
        this.timezone = "+7:00";
    }

    @Given("I have a user payload with firstName {string} and other required fields")
    public void iHaveAUserPayloadWithFirstNameAndOtherRequiredFields(String firstName) {
        this.title = "mr";
        this.firstName = firstName;
        this.lastName = "Doe";
        this.picture = "http://example.com/picture6.jpg";
        this.gender = "male";
        this.email = "johnathandoe66@example.com";
        this.dateOfBirth = "1985-07-15";
        this.phone = "777777777";
        this.street = "303 Oak St";
        this.city = "San Francisco";
        this.state = "CA";
        this.country = "USA";
        this.timezone = "-7:00";
    }

    @Given("I have a user payload with an empty email field")
    public void iHaveAUserPayloadWithAnEmptyEmailField() {
        this.title = "mrs";
        this.firstName = "Felicya";
        this.lastName = "Vernede";
        this.picture = "http://example.com/picture.jpg";
        this.gender = "female";
        this.email = "";
        this.dateOfBirth = "1990-01-01";
        this.phone = "123456789";
        this.street = "123 Main St";
        this.city = "New York";
        this.state = "NY";
        this.country = "USA";
        this.timezone = "+7:00";
    }

    @Given("I have a user payload with a non-string title")
    public void iHaveAUserPayloadWithANonStringTitle() {
        this.title = "123";
        this.firstName = "Emily";
        this.lastName = "Winston";
        this.picture = "http://example.com/picture1.jpg";
        this.gender = "female";
        this.email = "emily.winston333@example.com";
        this.dateOfBirth = "1995-05-20";
        this.phone = "(987) 654-3210";
        this.street = "456 Oak St";
        this.city = "Los Angeles";
        this.state = "CA";
        this.country = "USA";
        this.timezone = "-8:00";
    }

    @Given("I am authorized with an invalid app-id {string}")
    public void iHaveAnInvalidAppId(String appID) {
        this.httpRequest.header("app-id", appID);
    }

    @Given("I have a valid user payload")
    public void iHaveAValidUserPayload() {
        this.title = "mr";
        this.firstName = "Grace";
        this.lastName = "Madison";
        this.picture = "http://example.com/picture.jpg";
        this.gender = "female";
        this.email = "grace.madison123@example.com";
        this.dateOfBirth = "1990-01-01";
        this.phone = "1234567890";
        this.street = "123 Main Street";
        this.city = "New York";
        this.state = "NY";
        this.country = "USA";
        this.timezone = "-05:00";
    }

    @When("I send a POST request to create the user")
    public void iSendAPOSTRequestToCreateTheUser() {
        String requestBody = "{\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"gender\": \"" + gender + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"dateOfBirth\": \"" + dateOfBirth + "\",\n" +
                "  \"phone\": \"" + phone + "\",\n" +
                "  \"picture\": \"" + picture + "\",\n" +
                "  \"location\": {\n" +
                "    \"street\": \"" + street + "\",\n" +
                "    \"city\": \"" + city + "\",\n" +
                "    \"state\": \"" + state + "\",\n" +
                "    \"country\": \"" + country + "\",\n" +
                "    \"timezone\": \"" + timezone + "\"\n" +
                "  }\n" +
                "}";
        System.out.println("Request Payload: " + requestBody);
        httpRequest.body(requestBody);
        response = httpRequest.request(Method.POST, "/user/create");
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("I should receive a successful response with the status code {int}")
    public void iShouldReceiveSuccessWithStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Then("I should receive a response with the status code {int}")
    public void iShouldReceiveResponseWithStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }
}