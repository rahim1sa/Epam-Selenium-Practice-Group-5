package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveItemToCartTest {

    @Test
    public void addToCard() {
        System.setProperty(
                "webdriver.chrome.driver",
                "src/test/resources/webdriver/chromedriver.exe"
        );

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");

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
        Assert.assertEquals("1", cartAmount.getText());

        webDriver.close();
        webDriver.quit();
    }
}
