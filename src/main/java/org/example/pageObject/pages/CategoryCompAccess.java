package org.example.pageObject.pages;

import org.example.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryCompAccess extends BasePage  {

    public CategoryCompAccess (WebDriver webDriver) {
        super (webDriver);
    }

    public CategoryCompAccess open() {
        webDriver.get("https://www.amazon.com/b?node=16225007011");
        webDriver.manage().window().maximize();
        return this;
    }

    public ProductPage openProduct() {
//        String productTypeXpath = ("//span[contains(text(),'").concat(productType).concat("')]");
//        WebElement productMonitor = webDriver.findElement(By.xpath(productTypeXpath));
        WebElement productMonitor = webDriver.findElement(By.xpath("//span[contains(text(),'LED Monitor')]"));
        productMonitor.click();
        return new ProductPage(webDriver);
    }
}
