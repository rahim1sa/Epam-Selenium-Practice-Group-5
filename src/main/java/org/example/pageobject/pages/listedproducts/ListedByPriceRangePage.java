package org.example.pageobject.pages.listedproducts;

import org.example.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ListedByPriceRangePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"s-refinements\"]/div[12]/ul/li")
            public List<WebElement> priceRangeCount;

    @FindBy(xpath = "//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]")
            public List<WebElement> resultList;

    public ListedByPriceRangePage(WebDriver webDriver){super(webDriver);}

    public int priceRangeCount(){return priceRangeCount.size();}
    public String priceRange(int counter){
        return  waitForVisibility(webDriver.findElement(By.xpath(
                "//*[@id=\"s-refinements\"]/div[12]/ul/li[" + counter + "]/span/a"))).getText();
    }
    public void filterByPriceRange(int counter){
        webDriver.findElement(By.xpath(
                "//*[@id=\"s-refinements\"]/div[12]/ul/li[" + counter + "]/span/a/span")).click();
    }
    public int isPriceRangeFirstOrLast(int counter,String priceRange){
        if (counter == 1 || counter == priceRangeCount()) {
            return Integer.parseInt(priceRange.replaceAll("[^\\d.]", ""));
        }
        return 0;
    }

    public int Min(String priceRange) {
        return Integer.parseInt(priceRange.substring(1, priceRange.indexOf(" ")));
    }

    public int Max(String priceRange) {
        return Integer.parseInt(priceRange.substring(priceRange.lastIndexOf("$") + 1));
    }
}
