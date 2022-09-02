package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogItemPage extends BasePage {

    private WebElement deliveryBox;
    protected CatalogItemPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getDeliveryCountryName(){
        deliveryBox =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id = 'apex_offerDisplay_single_desktop']//div[@id = 'amazonGlobal_feature_div']")));
        return deliveryBox.getText();
    }
}
