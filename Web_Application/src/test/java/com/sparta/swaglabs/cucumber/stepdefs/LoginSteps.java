package com.sparta.swaglabs.cucumber.stepdefs;

import com.sparta.swaglabs.pom.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private StepDefManager manager;
    private LoginPage loginPage;

    public LoginSteps(StepDefManager manager) {
        this.manager = manager;
    }

    @Given("I am on the Login page")
    public void iAmOnTheLoginPage() {
        loginPage = new LoginPage(manager.getWebDriver());
    }

    @When("I type in a valid username in the username field")
    public void iTypeInAValidUsernameInTheUsernameField() {
        loginPage.fillWithUsername("standard_user");
    }

    @When("I type in an invalid username in the username field")
    public void iTypeInAnInvalidUsernameInTheUsernameField() {
        loginPage.fillWithUsername("guest");
    }

    @When("I type in a valid password in the password field")
    public void iTypeInAValidPasswordInThePasswordField() {
        loginPage.fillWithPassword("secret_sauce");
    }

    @When("I type in an invalid password in the password field")
    public void iTypeInAnInvalidPasswordInThePasswordField() {
        loginPage.fillWithPassword("guest98765");
    }

    @When("I press the Login button")
    public void iPressTheLoginButton() {
        loginPage.submit();
    }

    @Then("I should login successfully")
    public void iShouldLoginSuccessfully() {
        assertEquals("https://www.saucedemo.com/inventory.html", manager.getPageURL());
    }

    @Then("I should see an error message about invalid credentials")
    public void iShouldSeeAnErrorMessageAboutInvalidCredentials() {
        String expectedError = "Username and password do not match any user in this service";
        assertTrue(loginPage.getError().contains(expectedError));
    }

    @Then("I should see an error message {string}")
    public void userloginfailedandshowerrormessage(String expectedMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        assertTrue("Expected error message : '" + expectedMessage + "' not found. Found: " + actualErrorMessage,
                actualErrorMessage.contains(expectedMessage));
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        iAmOnTheLoginPage();
        iTypeInAValidUsernameInTheUsernameField();
        iTypeInAValidPasswordInThePasswordField();
        iPressTheLoginButton();
        iShouldLoginSuccessfully();
    }

    @When("I click menu button")
    public void iOpenTheMenu() {
        loginPage.openMenu();
    }

    @When("I click logout button")
    public void iClickTheLogoutButton() {
        loginPage.clickLogout();
    }

    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        assertEquals("https://www.saucedemo.com/", manager.getPageURL());
    }
}
