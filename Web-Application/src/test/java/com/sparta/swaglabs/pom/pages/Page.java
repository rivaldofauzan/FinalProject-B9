package com.sparta.swaglabs.pom.pages;

import org.openqa.selenium.WebDriver;

import java.util.Set;

/**
 * Superclass of all pages. Every page should extend this one
 */
public class Page {

    public WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public Set<String> getURls(){
        return driver.getWindowHandles();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
