package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveItemToCartTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                "src/test/resources/webdriver/chromedriver.exe"
        );

        this.webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }
    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void addToCart() {

        WebElement categoryCompAccess = webDriver.findElement(By.xpath("//a[@aria-label='Computers & Accessories']"));
        Assert.assertTrue(categoryCompAccess.isDisplayed());
        categoryCompAccess.click();

        WebElement productMonitor = webDriver.findElement(By.xpath("//span[contains(text(),'LED Monitor')]"));
        productMonitor.click();

        WebElement buttonAddToCart = webDriver.findElement(By.id("add-to-cart-button"));
        buttonAddToCart.click();

        WebElement addedToCard = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")));
        Assert.assertTrue(addedToCard.isDisplayed());

        WebElement closeSideSheet = new WebDriverWait(webDriver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-close_sideSheet-link")));
        closeSideSheet.click();

        WebElement cartAmount = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));

        Assert.assertEquals("1", cartAmount.getText(), "Adding to cart failed");
    }

    @Test
    public void removeFromCart() {

        WebElement categoryCompAccess = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//a[@aria-label='Computers & Accessories']"))));
        categoryCompAccess.click();

        WebElement productMonitor = webDriver.findElement(By.xpath("//span[contains(text(),'LED Monitor')]"));
        productMonitor.click();

        WebElement buttonAddToCart = webDriver.findElement(By.id("add-to-cart-button"));
        buttonAddToCart.click();

        WebElement closeSideSheet = new WebDriverWait(webDriver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-close_sideSheet-link")));
        closeSideSheet.click();

        WebElement cart = webDriver.findElement(By.id("nav-cart"));
        cart.click();

        WebElement cartDeleteBtn = webDriver.findElement(By.xpath("//input[@value='Delete']"));
        cartDeleteBtn.click();

        WebElement cartH1 = new WebDriverWait(webDriver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='a-spacing-mini a-spacing-top-base']")));
        Assert.assertEquals("Your Amazon Cart is empty.", cartH1.getText(), "Cart is not empty after product removing");

        WebElement cartPrice = webDriver.findElement(By.id("sc-subtotal-amount-activecart"));
        Assert.assertEquals(" $0.00", cartPrice.getText(), "Removing from cart failed");
    }
}
