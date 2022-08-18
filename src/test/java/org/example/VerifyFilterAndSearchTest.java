package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class VerifyFilterAndSearchTest {

    @Test
    public void verifyBrandName(){
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Asus\\Desktop\\Epam\\First_Selenium\\src\\test\\resources\\webdriver\\chromedriver.exe"
        );
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com");
        webDriver.manage().window().maximize();

        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();

        // filter by brand
        webDriver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[5]/ul/li[1]/span/a")).click();

        // taking category name
        WebElement titleApple = new WebDriverWait(webDriver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"p_89/Apple\"]/span/a/span")));

        // checking filtered item names whether it's matched with category name
        List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"a-size-base-plus a-color-base a-text-normal\")]"));
        for (WebElement x: resultList){
            Assert.assertTrue(x.getText().contains(titleApple.getText()));
        }

    }

    @Test
    public void verifyPriceRange(){
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Asus\\Desktop\\Epam\\First_Selenium\\src\\test\\resources\\webdriver\\chromedriver.exe"
        );
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com");
        webDriver.manage().window().maximize();

        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();

        // taking price range
        WebElement priceRange = webDriver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li[3]/span/a"));
        int max = Integer.parseInt(priceRange.getText().substring(priceRange.getText().length() - 3));
        int min = Integer.parseInt(priceRange.getText().substring(1, 3));

        // filter by price range
        webDriver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li[3]/span/a/span")).click();

        // checking price range
        List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]"));
        for (int i=0;i<resultList.size();i++){

            int wholePrice = Integer.parseInt(resultList.get(i).getText());
            if (wholePrice > min && wholePrice < max){
                System.out.println("Product-"+i+" price(" + wholePrice + ") is between "+min+" and "+max);
            }else {
                System.out.println("Product-"+i+" price(" + wholePrice + ") isn't between "+min+" and "+max);
            }
        }

    }

    @Test
    public void verifySortedPrice(){
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Asus\\Desktop\\Epam\\First_Selenium\\src\\test\\resources\\webdriver\\chromedriver.exe"
        );
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com");
        webDriver.manage().window().maximize();

        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();

        // filter by price range
        new WebDriverWait(webDriver,Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li[3]/span/a/span"))).click();

        // sort from "low" to "high"
        webDriver.findElement(By.xpath("//*[@id=\"a-autoid-0\"]/span")).click();
        webDriver.findElement(By.id("s-result-sort-select_1")).click();


        // verify sorted price
        List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]"));

        for (int i=0;i < resultList.size()-1;i++){
            int currentPrice = Integer.parseInt(resultList.get(i).getText());
            int nextPrice = Integer.parseInt(resultList.get(i+1).getText());

            if (currentPrice <= nextPrice ){
                System.out.println("ordered price-"+resultList.get(i+1).getText());
            }else {
                System.out.println("unordered price-"+resultList.get(i+1).getText());
            }
        }

    }
}
