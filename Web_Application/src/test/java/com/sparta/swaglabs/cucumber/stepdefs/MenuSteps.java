package com.sparta.swaglabs.cucumber.stepdefs;

import com.sparta.swaglabs.pom.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class MenuSteps {
    private ProductsPage productsPage;
    private StepDefManager stepDefStateManager;

    public MenuSteps(StepDefManager stepDefStateManager) {
        this.stepDefStateManager = stepDefStateManager;
    }

    @When("I click on the sidebar menu")
    public void iClickOnTheSidebarMenu() {
        productsPage = new ProductsPage(stepDefStateManager.getWebDriver());
        productsPage.goToMenu();
    }

    @And("I click the All Items link")
    public void iClickTheAllItemsLink() {
        productsPage.goToAllItemsMenu();
    }

    @Then("I am redirected to the Products page")
    public void iAmRedirectedToTheProductsPage() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", productsPage.getUrl());
    }

    @And("I click the About link")
    public void iClickTheAboutLink() {
        productsPage.goToAboutMenu();
    }

    @Then("I am redirected to saucelabs.com")
    public void iAmRedirectedToSaucelabsCom() {
        Assertions.assertEquals("https://saucelabs.com/", productsPage.getUrl());
    }

    @And("I click on the log out link")
    public void iClickOnTheLogOutLink() {
        productsPage.goToLogOutMenu();
    }

    @Then("I am logged out and redirected to the log in page")
    public void iAmLoggedOutAndRedirectedToTheLogInPage() {
        Assertions.assertEquals("https://www.saucedemo.com/", productsPage.getUrl());
    }

    @And("I click on Reset App State link")
    public void iClickOnResetAppStateLink() {
        productsPage.addTShirt(); //now there is 1 in the cart
        productsPage.goToResetAppStateMenu();
    }

    @Then("All items in the cart are removed")
    public void allItemsInTheCartAreRemoved() {
        Assertions.assertTrue(productsPage.resetProducts());
    }
}
