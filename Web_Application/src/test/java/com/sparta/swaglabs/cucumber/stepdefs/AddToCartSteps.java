package com.sparta.swaglabs.cucumber.stepdefs;

import com.sparta.swaglabs.pom.pages.ProductsPage;
import io.cucumber.java.en.When;

public class AddToCartSteps {
    private ProductsPage productsPage;
    private StepDefManager manager;

    public AddToCartSteps(StepDefManager manager) {
        this.manager = manager;
        productsPage = new ProductsPage(manager.getWebDriver()); // Ensure this is initialized
    }

}
