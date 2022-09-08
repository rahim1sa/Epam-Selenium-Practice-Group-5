package org.example.pageobject.pages.homepage;

import org.example.pageobject.BasePage;
import org.example.pageobject.pages.listedproducts.ListedByBrandNamePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[contains(@aria-label,\"Computers & Accessories\")]")
    private WebElement category;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage open() {
        webDriver.get("https://www.amazon.com");
        return this;
    }

    // redirecting category page
    public ListedByBrandNamePage clickCategory() {
        waitForVisibility(category).click();
        return new ListedByBrandNamePage(webDriver);
    }
}

