package org.example.pageobject.pages.listedproducts;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ListedBySortedPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"s-refinements\"]/div[12]/ul/li[3]/span/a")
    private WebElement priceRange;
    @FindBy(xpath = "//*[@id=\"a-autoid-0\"]/span")
    private WebElement popUpButton;
    @FindBy(id = "s-result-sort-select_1")
    private WebElement sortLowToHigh;
    @FindBy(xpath = "//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]")
    public List<WebElement> sortedProducts;
    public void filterByPrice(){
          waitForVisibility(priceRange).click();
    }
    public void sort(){
        waitForVisibility(popUpButton).click();
        waitForVisibility(sortLowToHigh).click();
    }

    public ListedBySortedPage(WebDriver webDriver) {super(webDriver);}
}
