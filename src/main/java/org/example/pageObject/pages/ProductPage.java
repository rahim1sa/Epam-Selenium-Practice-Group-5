package org.example.pageObject.pages;

import org.example.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    WebElement buttonAddToCart = webDriver.findElement(By.id("add-to-cart-button"));

    WebElement addedToCard = new WebDriverWait(webDriver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")));

    WebElement closeSideSheet = new WebDriverWait(webDriver,Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-close_sideSheet-link")));



    public ProductPage (WebDriver webDriver) {
        super (webDriver);
    }

    public ProductPage addToCart() {
        buttonAddToCart.click();
        return this;
    }

    public ProductPage closeSlideSheet () {
        closeSideSheet.click();
        return this;
    }


}
