package com.sparta.swaglabs.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
        goToLoginPage();
    }
    By errorMessage = By.cssSelector("[data-test='error']");

    private void goToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void fillWithUsername(String user) {
        driver.findElement(By.id("user-name")).sendKeys(user);
    }

    public void fillWithPassword(String pass) {
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    public void submit() {
        driver.findElement(By.id("login-button")).click();
    }

    public String getError() {
        List<WebElement> errorContainer = driver.findElements(By.className("error-message-container"));
        if (errorContainer.size() != 0) { // if there's an error
            return driver.findElement(By.className("error-message-container")).getText(); // return error message
        } else {
            return null;
        }
    }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    public void openMenu() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    public void clickLogout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    public void login(String user, String pass) {
        fillWithUsername(user);
        fillWithPassword(pass);
        submit();
    }
    public void logout() {
        openMenu();
        clickLogout();
    }
}
