package org.example;

import org.example.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver webDriver = new WebDriverFactory().getWebDriver();

    @BeforeMethod
    public void setup() {
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void clean() {
        webDriver.close();
        webDriver.quit();
    }
}
