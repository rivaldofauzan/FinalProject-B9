package com.sparta.swaglabs.pom.pages;

import com.sparta.swaglabs.pom.model.Product;
import com.sparta.swaglabs.pom.util.RandomIntGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsPage extends Page {
    private By addToCartButton = By.cssSelector(".btn_inventory");
    private List<WebElement> products;
    //Footer links
    private By twitterLink = new By.ByCssSelector(".social_twitter");
    private By facebookLink = new By.ByCssSelector(".social_facebook");
    private By linkedInLink = new By.ByCssSelector(".social_linkedin");

    //Menu
    private By Menu = new By.ByCssSelector("#react-burger-menu-btn");

    //Cart
    private By cartLink = new By.ByCssSelector(".shopping_cart_link");
    private By cartBadge = new By.ByCssSelector(".shopping_cart_badge");

    //Menu Links
    private By aboutMenu = new By.ByCssSelector("#about_sidebar_link");
    private By resetAppStateMenu = new By.ByCssSelector("#reset_sidebar_link");
    private By logOutMenu = new By.ByCssSelector("#logout_sidebar_link");
    private By allItemsLinkMenu = new By.ByCssSelector("#inventory_sidebar_link");

    //Products
    private By addTShirtToCart = new By.ByCssSelector("*[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private By addBagToCartLink = new By.ByCssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]");
    private By removeBagFromCartLink = new By.ByCssSelector("*[data-test=\"remove-sauce-labs-backpack\"]");

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
        goToProductsPage();
    }

    private void goToProductsPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public void goToTwitter() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(twitterLink).click();

    }

    public void goToFacebook() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(facebookLink).click();

    }

    public void goTolinkedIn() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        driver.findElement(linkedInLink).click();
    }

    public CartPage goToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
        return new CartPage(driver);
    }

    public void addBagToCart(){
        driver.findElement(addBagToCartLink).click();
    }
    public void goToMenu() {
        driver.findElement(Menu).click();
    }

    public void goToAllItemsMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(allItemsLinkMenu));
        element.click();
    }

    public void goToAboutMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(aboutMenu));
        element.click();
    }

    public void goToLogOutMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(logOutMenu));
        element.click();
    }

    public void goToResetAppStateMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(resetAppStateMenu));
        element.click();
    }

    public void addTShirt() {
        driver.findElement(addTShirtToCart).click();
    }

    public boolean resetProducts() {
        List<WebElement> webElementList = driver.findElements(cartBadge);
        return webElementList.size() == 0;
    }


    public void addRandomProductToCart() {
        products = driver.findElements(By.className("inventory_item"));
        int randomIndex = new Random().nextInt(products.size());
        products.get(randomIndex).findElement(addToCartButton).click();
    }

    public void addAllProductsToCart() {
        for (WebElement product : products) {
            product.findElement(addToCartButton).click();
        }
    }


    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        // get all elements that are products
        List<WebElement> elements = driver.findElements(By.className("inventory_item"));

        // for each element found, create a Product from it
        for (WebElement e : elements) {
            String name = e.findElement(By.className("inventory_item_name")).getText();
            String desc = e.findElement(By.className("inventory_item_desc")).getText();
            String imageUrl = e.findElement(By.tagName("img")).getAttribute("src");

            // delete $ and . from the price, store as an int
            String priceStr = e.findElement(By.className("inventory_item_price")).getText();
            priceStr = priceStr.replace("$", "").replace(".", "");
            int price = Integer.parseInt(priceStr);

            // extract product id from the link's "id" attribute
            String idStr = e.findElement(By.tagName("a")).getAttribute("id");
            Pattern p = Pattern.compile("\\d+"); // match numbers
            Matcher m = p.matcher(idStr);
            int id = -1;
            if (m.find()) {
                id = Integer.parseInt(m.group(0));
            }

            // create product and add it to the ArrayList
            products.add(new Product(id, name, desc, price, imageUrl));
        }
        return products;
    }

    public boolean isEmpty() {
        if(getProducts().size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int clickRandomProductImage() {
        List<WebElement> elements = driver.findElements(By.className("inventory_item"));
        int randInt = RandomIntGenerator.generate(0, elements.size());
        WebElement randProduct = elements.get(RandomIntGenerator.generate(0, elements.size()));

        // extract product id from the link's "id" attribute
        String idStr = randProduct.findElement(By.tagName("a")).getAttribute("id");
        Pattern p = Pattern.compile("\\d+"); // match numbers
        Matcher m = p.matcher(idStr);
        int id = -1;
        if (m.find()) {
            id = Integer.parseInt(m.group(0));
        }

        randProduct.findElement(By.className("inventory_item_img")).click();
        //System.out.println("Clicked product in position " + (randInt + 1) + " (id=" + id + ")");

        return id;
    }

    public int clickRandomProductName() {
        List<WebElement> elements = driver.findElements(By.className("inventory_item"));
        int randInt = RandomIntGenerator.generate(0, elements.size());
        WebElement randProduct = elements.get(randInt);

        // extract product id from the link's "id" attribute
        String idStr = randProduct.findElement(By.tagName("a")).getAttribute("id");
        Pattern p = Pattern.compile("\\d+"); // match numbers
        Matcher m = p.matcher(idStr);
        int id = -1;
        if (m.find()) {
            id = Integer.parseInt(m.group(0));
        }

        randProduct.findElement(By.className("inventory_item_name")).click();
        //System.out.println("Clicked product in position " + (randInt + 1) + " (id=" + id + ")");

        return id;
    }

    public int numberOfItemsInCart(){
        List<WebElement> webElementList = driver.findElements(cartBadge);
        return webElementList.size();
    }

    public void removeBackpackFromCartWhenOnProductsPage(){
        driver.findElement(removeBagFromCartLink).click();
    }

    public void clickFilterDropdown() {
        driver.findElement(By.className("select_container")).click();
    }

    public void selectNameAZ() {
        driver.findElement(By.cssSelector("option[value='az']")).click();
    }

    public void selectNameZA() {
        driver.findElement(By.cssSelector("option[value='za']")).click();
    }

    public void selectPriceLowToHigh() {
        driver.findElement(By.cssSelector("option[value='lohi']")).click();
    }

    public void selectPriceHighToLow() {
        driver.findElement(By.cssSelector("option[value='hilo']")).click();
    }

    public String getCurrentFilter() {
        return driver.findElement(By.className("active_option")).getText();
    }
    public String getBackpackDescription(){
        Product bag=null;
        ArrayList<Product> allItems=getProducts();
        for (Product p: allItems) {
            if (p.getName().contains("Backpack")) {
                bag= p;
            }
        }
        return bag.getDesc();
    }

    public boolean imagesAreUnique() {
        ArrayList<String> imageURLs = new ArrayList<>();
        getProducts().stream().forEach(p -> imageURLs.add(p.getImageUrl()));
        Set<String> set = new HashSet<String>(imageURLs);
        if(set.size() < imageURLs.size()){
            return false; // there are duplicates
        } else {
            return true;
        }
    }
    public List<String> getBackpackAndTshirtInfo(){
        List<String> itemsNeeded=new ArrayList<String>();
        ArrayList<Product> allItems=getProducts();
        for (Product p: allItems) {
            if (p.getName().contains("Backpack") || p.getName().contains("Bolt T-Shirt")) {
                itemsNeeded.add(p.getDesc());
            }
        }
        return itemsNeeded;
    }
}
