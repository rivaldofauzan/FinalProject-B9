package com.sparta.swaglabs.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends Page {
    private By removeBagFromCartLink = new By.ByCssSelector("*[data-test=\"remove-sauce-labs-backpack\"]");
    private By cartBadge = new By.ByCssSelector(".shopping_cart_badge");
    private By emptyCartMessage = new By.ByCssSelector(".cart_item_label .removed_cart_item");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        goToCart();
    }

    private void goToCart() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    public void removeBackpackFromCartWhenOnCartPage() {
        driver.findElement(removeBagFromCartLink).click();
    }

    public int numberOfItemsInCart() {
        List<WebElement> webElementList = driver.findElements(cartBadge);
        return webElementList.size();
    }

     public List<String> getDescOfItemsInCart() {
        List<WebElement> items = driver.findElements(By.className("inventory_item_desc"));
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public CheckoutInformationPage checkout() {
        driver.findElement(By.id("checkout")).click();
        return new CheckoutInformationPage(driver);
    }

    public ProductsPage continueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
        return new ProductsPage(driver);
    }

    public boolean isEmptyCartMessageDisplayed() {
        List<WebElement> emptyMessageElements = driver.findElements(emptyCartMessage);
        return !emptyMessageElements.isEmpty();
    }

    public String getEmptyCartMessage() {
        if (isEmptyCartMessageDisplayed()) {
            return driver.findElement(emptyCartMessage).getText();
        } else {
            return "";
        }
    }
}
