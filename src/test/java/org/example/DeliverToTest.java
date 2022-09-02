package org.example;

import org.example.pageObject.CatalogItemPage;
import org.example.pageObject.CatalogPage;
import org.example.pageObject.HomePage;
import org.example.pageObject.modules.ZipCodeInputModule;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeliverToTest extends BaseTest{

    @Test
    public void verifyZipCodeCorrectUpdateOnChangeInDeliverToSection(){
        var zipCode = "36104";
        HomePage homePage = new HomePage(webDriver);
        ZipCodeInputModule zipCodeInputModule = homePage.open().clickDeliverTo();
        zipCodeInputModule.setZipCodeAndApply(zipCode);
        homePage.waitForReload();
        var deliverToText = homePage.getDeliverToText();
        Assert.assertTrue(deliverToText.contains(zipCode),
                "Delivery To Textbox doesn't contain the required zipcode");
    }

    @Test
    public void verifyDeliverToListOfCountiesContainsPoland(){
        var countryName = "Poland";
        HomePage homePage = new HomePage(webDriver);
        ZipCodeInputModule zipCodeInputModule = homePage.open().clickDeliverTo();
        Assert.assertTrue(
                zipCodeInputModule.deliverToListOfCounties().stream().anyMatch(c -> c.equals(countryName)),
                "List of countries doesn't contain " + countryName);
    }

    @Test
    public void verifyShippingToSectionContainsDeliverToCountry(){
        var countryName = "Poland";
        HomePage homePage = new HomePage(webDriver);
        ZipCodeInputModule zipCodeInputModule = homePage.open().clickDeliverTo();
        zipCodeInputModule.chooseCountryAndApply(countryName);
        homePage.waitForReload();
        CatalogPage catalogPage = homePage.clickHeadsetsCatalog();
        CatalogItemPage catalogItemPage = catalogPage.clickCatalogItem();
        Assert.assertTrue(catalogItemPage.getDeliveryCountryName().contains(countryName),
                "Catalog item delivery country is not " + countryName);
    }
}