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

    WebElement closeSideSheet = new WebDriverWait(webDriver,Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-close_sideSheet-link")));

    WebElement cartAmount = new WebDriverWait(webDriver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));


    public ProductPage (WebDriver webDriver) {
        super (webDriver);
    }

    public ProductPage open () {
        webDriver.get("https://www.amazon.com/Sceptre-E248W-19203R-Monitor-Speakers-Metallic/dp/B0773ZY26F/ref=lp_16225007011_1_3");
        webDriver.manage().window().maximize();
        return this;
    }

    public ProductPage addToCart() {
        buttonAddToCart.click();
        return this;
    }

    public ProductPage closeSlideSheet () {
        closeSideSheet.click();
        return this;
    }

    public String takeCartAmount () {
        return cartAmount.getText();
    }

}
