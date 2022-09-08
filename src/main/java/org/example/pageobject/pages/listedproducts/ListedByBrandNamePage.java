package org.example.pageobject.pages.listedproducts;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListedByBrandNamePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"s-refinements\"]/div[5]/ul/li[1]/span/a")
    private WebElement filterByBrand;

    @FindBy(xpath = "//*[@id=\"brandsRefinements\"]//*[@class=\"a-spacing-micro\"][1]")
    private WebElement titleBrand;

    @FindBy(xpath = "//*[contains(@class,\"a-size-base-plus a-color-base a-text-normal\")]")
    private List<WebElement> listElements;

    public ListedByBrandNamePage(WebDriver webDriver) {
        super(webDriver);
    }

    // filter by brand
    public ListedByBrandNamePage filterByBrand() {
        filterByBrand.click();
        return this;
    }

    // getting brand title
    public String getTitle() {
        return waitForVisibility(titleBrand).getText();
    }

    // getting listed products
    public List<WebElement> getList() {
        return listElements;
    }
}
