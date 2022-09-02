package org.example.pageObject;

import org.example.pageObject.modules.ZipCodeInputModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private WebElement deliverToElement;

    @FindBy(xpath = "//span[@id= 'nav-global-location-data-modal-action']//span[contains(@id, 'glow-ingress-line2')]")
    private WebElement deliverToTextBoxElement;

    @FindBy(xpath = "//div[@id = 'gw-layout']//a[contains(@aria-label, 'Headsets')]")
    private WebElement buttonHeadsets;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public ZipCodeInputModule clickDeliverTo(){
        deliverToElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']")));
        deliverToElement.click();
        return new ZipCodeInputModule(webDriver);
    }

    public String getDeliverToText(){
        return deliverToTextBoxElement.getText();
    }

    public CatalogPage clickHeadsetsCatalog(){
        buttonHeadsets.click();
        return new CatalogPage(webDriver);
    }

    public void waitForReload(){
        try {
            wait.until(ExpectedConditions.stalenessOf(deliverToElement));
        }
        catch(Exception ex){}
    }
}
