package org.example;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SearchFieldTest {
    String path = System.getProperty("user.dir");

    @Test
    public void incorrectInfo() {
        System.setProperty("webdriver.chrome.driver", path+"\\resources\\webdriver\\chromedriver.exe");

        open("https://amazon.com");

        $(By.id("twotabsearchtextbox")).setValue("gufireowpdvi0f9u8b7g9348jgrm39505");

        $(By.id("nav-search-submit-button")).click();

        $(By.cssSelector("span.a-size-medium:nth-child(1)")).shouldHave(text("No results for"));
    }

    @Test
    public void correctInfo() {
        System.setProperty("webdriver.chrome.driver", path+"\\resources\\webdriver\\chromedriver.exe");

        open("https://amazon.com");

        $(By.id("twotabsearchtextbox")).setValue("laptop");

        $(By.id("nav-search-submit-button")).click();

        $(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/span/div/div/span")).shouldHave(text("RESULTS"));
    }

    @Test
    public void checkResults() {
        System.setProperty("webdriver.chrome.driver", path+"\\resources\\webdriver\\chromedriver.exe");

        open("https://amazon.com");

        $(By.id("twotabsearchtextbox")).setValue("laptop");

        $(By.id("nav-search-submit-button")).click();

        $(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[5]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")).shouldHave(text("Laptop"));

    }
}
