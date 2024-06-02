package com.sparta.swaglabs.cucumber.stepdefs;

import com.sparta.swaglabs.pom.pages.CartPage;
import com.sparta.swaglabs.pom.pages.LoginPage;
import com.sparta.swaglabs.pom.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CartSteps {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private StepDefManager manager;

    public CartSteps(StepDefManager manager) {
        this.manager = manager;
    }

    @Given("I am on the Dashboard page")
    public void iAmOnTheDashboardPage() {
        loginPage = new LoginPage(manager.getWebDriver());
        loginPage.login("standard_user", "secret_sauce");
        productsPage = loginPage.getRedirect();
    }

    @When("I click the \"Add To Cart\" button next to an item")
    public void iClickTheButtonNextToAnItem() {
        productsPage.addBagToCart();
    }

    @When("I add {int} random product\\(s) to the cart")
    public void iAddRandomProductsToTheCart(int count) {
        for (int i = 0; i < count; i++) {
            productsPage.addRandomProductToCart();
        }
    }

    @When("I click the cart icon")
    public void iClickTheCartIcon() {
        cartPage = productsPage.goToCart();
    }

    @Then("I see {int} product\\(s) in the cart with descriptions, names, and total prices")
    public void iSeeProductsInTheCartWithDescriptionsNamesAndTotalPrices(Integer expectedCount) {
        List<String> descriptions = cartPage.getDescOfItemsInCart();
        Assertions.assertEquals(expectedCount.intValue(), descriptions.size(), "The number of items in the cart does not match the expected count.");
    }
}
