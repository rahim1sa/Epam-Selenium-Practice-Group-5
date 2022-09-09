package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.time.Duration;
import java.util.Iterator;

import static org.testng.Assert.assertTrue;

public class SearchFieldTest {

    @Test
    public void incorrectInfo() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\y4ken\\Documents\\GitHub\\Epam-Selenium-Practice-Group-5\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.get("https://amazon.com");

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("gufireowpdvi0f9u8b7g9348jgrm39505");

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement resultsLabel = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-size-medium:nth-child(1)")));
        Assert.assertEquals("No results for", resultsLabel.getText());

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void correctInfo() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\y4ken\\Documents\\GitHub\\Epam-Selenium-Practice-Group-5\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.get("https://amazon.com");

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("laptop");

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement resultsLabel = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/span/div/div/span")));
        Assert.assertEquals("RESULTS", resultsLabel.getText());

        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void checkResults() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\y4ken\\Documents\\GitHub\\Epam-Selenium-Practice-Group-5\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.get("https://amazon.com");

        WebElement searchField = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("laptop");

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement resultsLabel = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[5]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")));
        Assert.assertTrue(resultsLabel.getText().toLowerCase().contains("laptop"));

        webDriver.close();
        webDriver.quit();
    }
}
