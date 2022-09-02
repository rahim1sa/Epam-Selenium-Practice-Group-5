package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends BasePage {

    private WebElement buttonHeadsetsMicro;
    protected CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CatalogItemPage clickCatalogItem(){
        buttonHeadsetsMicro =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'search']//div[@cel_widget_id= 'MAIN-SEARCH_RESULTS-1']//div[contains(@class, 'a-section')]//div[contains(@class, 'sg-col')]")));
        buttonHeadsetsMicro.click();
        return new CatalogItemPage(webDriver);
    }
}
