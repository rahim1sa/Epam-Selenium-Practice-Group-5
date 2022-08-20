package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class DeliverToTest {

    public WebDriver webDriver;
    public WebDriverWait wait;

    //creating methods for setup/quit

    @BeforeMethod
    public void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Yanina\\Documents\\IT\\EPAM\\Verify-Deliver-To-functionality-MyCode\\src\\test\\resources\\WebDriver\\chromedriver.exe");

        webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void clean() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void verifyZipCode() {
        var zipCode = "36104";

        WebElement deliverTo = webDriver.findElement(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']"));
        deliverTo.click();

        WebElement zipCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#GLUXZipUpdateInput")));

        zipCodeInput.sendKeys(zipCode);

        WebElement applyButton = webDriver.findElement(By.cssSelector("#GLUXZipUpdate"));
        applyButton.click();

        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'a-popover-footer')]//input[contains(@id, 'GLUXConfirmClose')]")));

        continueButton.click();

        //waiting till the old element will be deleted from DOM, it indicates that the page was refreshed

        boolean waitReload = wait.until(ExpectedConditions.stalenessOf(deliverTo));

        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id= 'nav-global-location-data-modal-action']//span[contains(@id, 'glow-ingress-line2')]")));
        Assert.assertTrue(textBox.getText().contains(zipCode));
    }

    @Test
    public void verifyListOfCounties() {
        var countryName = "Poland";
        WebElement deliverTo = webDriver.findElement(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']"));
        deliverTo.click();

        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'GLUXCountryList']")));

        //searching through list of countries

        Select select = new Select(selectElement);
        List<WebElement> selectList = select.getOptions();
        Assert.assertTrue(selectList.stream().anyMatch(c -> c.getText().equals(countryName)));
    }

    @Test
    public void verifyCountry() {
        var countryName = "Poland";

        WebElement deliverTo = webDriver.findElement(By.xpath("//div/span[@id= 'nav-global-location-data-modal-action']"));
        deliverTo.click();

        WebElement buttonDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'GLUXCountryListDropdown']")));
        buttonDropDown.click();

        WebElement countryPoland = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(),'" + countryName + "')]")));
        countryPoland.click();

        WebElement buttonDone = webDriver.findElement(By.xpath("//button[contains(@name, 'glowDoneButton')]"));
        buttonDone.click();

        boolean waitReload = wait.until(ExpectedConditions.stalenessOf(deliverTo));

        WebElement buttonHeadsets = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'gw-layout']//a[contains(@aria-label, 'Headsets')]")));

        buttonHeadsets.click();

        WebElement buttonHeadsetsMicro = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'search']//div[@cel_widget_id= 'MAIN-SEARCH_RESULTS-1']//div[contains(@class, 'a-section')]//div[contains(@class, 'sg-col')]")));
        buttonHeadsetsMicro.click();

        WebElement deliveryBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'apex_offerDisplay_single_desktop']//div[@id = 'amazonGlobal_feature_div']")));
        Assert.assertTrue(deliveryBox.getText().contains(countryName));

    }
}
