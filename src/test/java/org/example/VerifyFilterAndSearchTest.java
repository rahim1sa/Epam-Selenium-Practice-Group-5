package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class VerifyFilterAndSearchTest {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    int min=0, max=0, price=0, wholePrice=0;

    @BeforeMethod
    public void setup(){
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Asus\\Desktop\\Epam\\First_Selenium\\src\\test\\resources\\webdriver\\chromedriver.exe"
        );
        webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com");
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    @AfterMethod
    public void quite(){
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void verifyBrandName(){
        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();

        // filter by brand
        webDriver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[5]/ul/li[1]/span/a")).click();

        // taking category name
        WebElement titleApple =webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"p_89/Apple\"]/span/a/span")));

        // checking filtered item names whether it's matched with category name
        List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"a-size-base-plus a-color-base a-text-normal\")]"));
        for (WebElement x: resultList){
            Assert.assertTrue(x.getText().contains(titleApple.getText()));
        }
    }


    public int findMin(String priceRange){
        min = Integer.parseInt(priceRange.substring(1,priceRange.indexOf(" ")));
        return min;
    }
    public int findMax(String priceRange){
        max = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf("$") + 1));
        return max;
    }
    @Test
    public void verifyPriceRange(){

        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();

        int priceRangeCount = webDriver.findElements(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li")).size();

        for (int i=1;i <= priceRangeCount;i++){
            // taking price range
            String priceRange = webDriver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li["+ i +"]/span/a")).getText();

            // filter by price range
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li["+ i +"]/span/a/span"))).click();

            // checking price range
            List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]"));
            for (int j=0;j<resultList.size();j++){
                if (i==1 || i==priceRangeCount)  {
                    price = Integer.parseInt(priceRange.replaceAll("[^\\d.]", ""));
                }
                wholePrice = Integer.parseInt(resultList.get(j).getText());
                if (priceRange.contains("Under")){
                    Assert.assertTrue(wholePrice > price,"Product-"+j+" price(" + wholePrice + ") is under "+price);
                }else if(priceRange.contains("Above")) {
                    Assert.assertTrue(wholePrice > price, "Product-"+j+" price(" + wholePrice + ") is under "+price);
                }else {
                    Assert.assertTrue(wholePrice > findMin(priceRange) && wholePrice < findMax(priceRange)
                            ,"Product-"+j+" price(" + wholePrice + ") is not between "+findMin(priceRange)+" and "+findMax(priceRange));
                }
            }
            webDriver.navigate().back();
        }

    }

    @Test
    public void verifySortedPrice(){

        // redirecting category page
        webDriver.findElement(By.xpath("//*[contains(@aria-label,\"Computers & Accessories\")]")).click();
        // filter by price range
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s-refinements\"]/div[12]/ul/li[3]/span/a/span"))).click();
        // sort from "low" to "high"
        webDriver.findElement(By.xpath("//*[@id=\"a-autoid-0\"]/span")).click();
        webDriver.findElement(By.id("s-result-sort-select_1")).click();
        // verify sorted price
        List<WebElement> resultList = webDriver.findElements(By.xpath("//*[contains(@class,\"s-result-list\")]//*[contains(@class,\"a-price-whole\")]"));

        for (int i=0;i < resultList.size()-1;i++){
            int currentPrice = Integer.parseInt(resultList.get(i).getText());
            int nextPrice = Integer.parseInt(resultList.get(i+1).getText());

            Assert.assertTrue(currentPrice <= nextPrice,"unordered price-"+resultList.get(i+1).getText() );
        }
    }
}
