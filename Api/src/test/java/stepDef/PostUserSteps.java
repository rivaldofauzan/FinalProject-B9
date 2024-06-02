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

    @Given("I am authorized with an invalid app-id {string}")
    public void iHaveAnInvalidAppId(String appID) {
        this.httpRequest.header("app-id", appID);
    }

    @Given("I am authorized with an incorrectly set app-id {string}")
    public void iHaveAnIncorrectlySetAppId(String appID) {
        this.httpRequest.header("app-key", appID);
    }

    @Given("I have a user payload with title {string} and other required fields")
    public void iHaveAUserPayloadWithTitleAndOtherRequiredFields(String title) {
        this.title = title;
        this.firstName = "Michael";
        this.lastName = "Johnson";
        this.picture = "http://example.com/picture3.jpg";
        this.gender = "male";
        this.email = "michael.johnson456@example.com";
        this.dateOfBirth = "1988-12-10";
        this.phone = "987654321";
        this.street = "456 Elm St";
        this.city = "Chicago";
        this.state = "IL";
        this.country = "USA";
        this.timezone = "-6:00";
    }

    @Given("I have a user payload with firstName {string} and other required fields")
    public void iHaveAUserPayloadWithFirstNameAndOtherRequiredFields(String firstName) {
        this.title = "dr";
        this.firstName = firstName;
        this.lastName = "Smith";
        this.picture = "http://example.com/picture4.jpg";
        this.gender = "female";
        this.email = "jane.smith78@example.com";
        this.dateOfBirth = "1992-04-25";
        this.phone = "555555555";
        this.street = "789 Pine St";
        this.city = "Houston";
        this.state = "TX";
        this.country = "USA";
        this.timezone = "-6:00";
    }

    @Given("I have a user payload with an empty email field")
    public void iHaveAUserPayloadWithAnEmptyEmailField() {
        this.title = "ms";
        this.firstName = "Olivia";
        this.lastName = "Brown";
        this.picture = "http://example.com/picture5.jpg";
        this.gender = "female";
        this.email = "";
        this.dateOfBirth = "1995-02-14";
        this.phone = "123123123";
        this.street = "101 Maple St";
        this.city = "Phoenix";
        this.state = "AZ";
        this.country = "USA";
        this.timezone = "-7:00";
    }

    @Given("I have a user payload with a non-string title")
    public void iHaveAUserPayloadWithANonStringTitle() {
        this.title = "321";
        this.firstName = "Emma";
        this.lastName = "Davis";
        this.picture = "http://example.com/picture6.jpg";
        this.gender = "female";
        this.email = "emma.davis987@example.com";
        this.dateOfBirth = "1993-08-22";
        this.phone = "(321) 654-9870";
        this.street = "102 Birch St";
        this.city = "San Diego";
        this.state = "CA";
        this.country = "USA";
        this.timezone = "-8:00";
    }

    @Given("I have a valid user payload")
    public void iHaveAValidUserPayload() {
        this.title = "mr";
        this.firstName = "Ethan";
        this.lastName = "Williams";
        this.picture = "http://example.com/picture7.jpg";
        this.gender = "male";
        this.email = "ethan.williams123@example.com";
        this.dateOfBirth = "1989-11-30";
        this.phone = "9876543210";
        this.street = "123 Cedar St";
        this.city = "Denver";
        this.state = "CO";
        this.country = "USA";
        this.timezone = "-7:00";
    }
    @Given("I have a user payload with firstName {string}, lastName {string}, and email {string}")
    public void iHaveAUserPayloadWithFirstNameLastNameAndEmail(String firstName, String lastName, String email) {
        this.title = "mr"; // Assuming default title
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = "http://example.com/defaultPicture.jpg"; // Assuming a default picture
        this.gender = "male"; // Assuming default gender
        this.email = email;
        this.dateOfBirth = "1980-01-01"; // Assuming a default date of birth
        this.phone = "1234567890"; // Assuming a default phone number
        this.street = "123 Main St"; // Assuming a default street address
        this.city = "Anytown"; // Assuming a default city
        this.state = "CA"; // Assuming a default state
        this.country = "USA"; // Assuming a default country
        this.timezone = "-8:00"; // Assuming a default timezone
    }

    @Then("the response body should contain user data")
    public void theResponseBodyShouldContainUserData() {
        String responseBody = response.getBody().asString();
    
        // Validate that the response contains the expected user data
        assertThat(responseBody, containsString("\"title\":\"" + title + "\""));
        assertThat(responseBody, containsString("\"firstName\":\"" + firstName + "\""));
        assertThat(responseBody, containsString("\"lastName\":\"" + lastName + "\""));
        assertThat(responseBody, containsString("\"gender\":\"" + gender + "\""));
        assertThat(responseBody, containsString("\"email\":\"" + email + "\""));
        assertThat(responseBody, containsString("\"dateOfBirth\":\"" + dateOfBirth + "\""));
        assertThat(responseBody, containsString("\"phone\":\"" + phone + "\""));
        assertThat(responseBody, containsString("\"picture\":\"" + picture + "\""));
        assertThat(responseBody, containsString("\"street\":\"" + street + "\""));
        assertThat(responseBody, containsString("\"city\":\"" + city + "\""));
        assertThat(responseBody, containsString("\"state\":\"" + state + "\""));
        assertThat(responseBody, containsString("\"country\":\"" + country + "\""));
        assertThat(responseBody, containsString("\"timezone\":\"" + timezone + "\""));
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

    @Then("the response body should contain {string}")
    public void theResponseBodyShouldContain(String expectedMessage) {
        assertThat(response.getBody().asString(), containsString(expectedMessage));
    }
}
