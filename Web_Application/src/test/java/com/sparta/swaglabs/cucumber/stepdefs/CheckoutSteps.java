package com.sparta.swaglabs.cucumber.stepdefs;

import com.sparta.swaglabs.pom.pages.CartPage;
import com.sparta.swaglabs.pom.pages.CheckoutCompletePage;
import com.sparta.swaglabs.pom.pages.CheckoutInformationPage;
import com.sparta.swaglabs.pom.pages.CheckoutOverviewPage;
import com.sparta.swaglabs.pom.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutSteps {

    private StepDefManager manager;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    private ProductsPage productsPage;

    public CheckoutSteps(StepDefManager manager) {
        this.manager = manager;
    }

    @Given("I am on the Checkout Information page")
    public void iAmOnTheCheckoutInformationPage() {
        checkoutInformationPage = new CheckoutInformationPage(manager.getWebDriver());
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", manager.getPageURL());
    }

    @When("I fill in first name with {string}")
    public void iFillInFirstNameWith(String firstName) {
        checkoutInformationPage.fillFirstName(firstName);
    }

    @When("I fill in last name with {string}")
    public void iFillInLastNameWith(String lastName) {
        checkoutInformationPage.fillLastName(lastName);
    }

    @When("I fill in Zip\\/Postal Code with {string}")
    public void iFillInZipPostalCodeWith(String zipCode) {
        checkoutInformationPage.fillPostCode(zipCode);
    }

    @And("I click the Continue button")
    public void iClickTheContinueButton() {
        checkoutInformationPage.cont();
    }

    @Then("I go to the Checkout Overview page")
    public void iGoToTheCheckoutOverviewPage() {
        checkoutOverviewPage = new CheckoutOverviewPage(manager.getWebDriver());
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", manager.getPageURL());
    }

    @When("I press the Finish button")
    public void iPressTheFinishButton() {
        if (checkoutOverviewPage == null) {
            throw new NullPointerException("checkoutOverviewPage is null");
        }
        checkoutOverviewPage.finish();
    }

    @When("I press the Cancel button")
    public void iPressTheCancelButton() {
        if (checkoutOverviewPage == null) {
            throw new NullPointerException("checkoutOverviewPage is null");
        }
        checkoutOverviewPage.cancel();
    }

    @Then("I go to the Checkout Complete page")
    public void iGoToTheCheckoutCompletePage() {
        checkoutCompletePage = new CheckoutCompletePage(manager.getWebDriver());
        assertEquals("https://www.saucedemo.com/checkout-complete.html", manager.getPageURL());
    }

    @When("I press the Back Home button")
    public void iPressTheBackHomeButton() {
        if (checkoutCompletePage == null) {
            throw new NullPointerException("checkoutCompletePage is null");
        }
        productsPage = checkoutCompletePage.goToProductsPage();
    }

    @Then("I go to the cart page")
    public void iGoToTheCartPage() {
        cartPage = new CartPage(manager.getWebDriver());
        assertEquals("https://www.saucedemo.com/cart.html", manager.getPageURL());
    }

    @Then("I go to the Product page")
    public void iGoToTheProductPage() {
        assertEquals("https://www.saucedemo.com/inventory.html", manager.getPageURL());
    }
}
