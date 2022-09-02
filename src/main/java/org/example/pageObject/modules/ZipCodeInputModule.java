package org.example.pageObject.modules;

import org.example.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ZipCodeInputModule extends BasePage {
    private WebElement zipCodeInput;
    private WebElement applyButton;
    private WebElement continueButton;
    private WebElement selectElement;

    private WebElement buttonDropDown;

    private WebElement countryPoland;

    private WebElement buttonDone;

    public ZipCodeInputModule(WebDriver webDriver) {
        super(webDriver);
    }

    public void setZipCodeAndApply(String zipCode){
        zipCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#GLUXZipUpdateInput")));
        zipCodeInput.sendKeys(zipCode);
        applyButton = webDriver.findElement(By.cssSelector("#GLUXZipUpdate"));
        applyButton.click();
        continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'a-popover-footer')]//input[contains(@id, 'GLUXConfirmClose')]")));
        continueButton.click();
    }

    public List<String> deliverToListOfCounties(){
        selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id = 'GLUXCountryList']")));
        Select select = new Select(selectElement);
        return select.getOptions().stream().map(c -> c.getText()).collect(Collectors.toList());
    }

    public void chooseCountryAndApply(String countryName){
        buttonDropDown =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'GLUXCountryListDropdown']")));
        buttonDropDown.click();

        countryPoland =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[contains(text(),'" + countryName + "')]")));
        countryPoland.click();

        buttonDone =
                webDriver.findElement(By.xpath("//button[contains(@name, 'glowDoneButton')]"));
        buttonDone.click();
    }
}

