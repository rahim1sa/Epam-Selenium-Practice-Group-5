package org.example;

import org.example.pageobject.pages.homepage.HomePage;
import org.example.pageobject.pages.listedproducts.ListedByBrandNamePage;
import org.example.pageobject.pages.listedproducts.ListedByPriceRangePage;
import org.example.pageobject.pages.listedproducts.ListedBySortedPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class VerifyFilterAndSearchTest extends BaseTest{
    public int price = 0, wholePrice = 0;

    @Test
    public void verifyBrandName() {
        HomePage homepage = new HomePage(webDriver);
        ListedByBrandNamePage brandName = new ListedByBrandNamePage(webDriver);

        // listed products
        List<WebElement> filteredList = homepage.open()
                .clickCategory().filterByBrand().getList();

        // brand name
        String titleBrand = brandName.getTitle();

        // checking filtered item names whether it's matched with brand name
        for (WebElement x : filteredList) {
            Assert.assertTrue(x.getText().contains(titleBrand));
        }
    }


    @Test
    public void verifyPriceRange() {
        HomePage homepage = new HomePage(webDriver);
        ListedByPriceRangePage listPG = new ListedByPriceRangePage(webDriver);

        homepage.open().clickCategory();

        for (int i = 1; i <= listPG.priceRangeCount(); i++) {

            // taking price range
            String priceRange = listPG.priceRange(i);
            // filter by price range
            listPG.filterByPriceRange(i);
            // checking price range
            for (int j = 0; j < listPG.resultList.size(); j++) {
                price = listPG.isPriceRangeFirstOrLast(i,priceRange);
                wholePrice = Integer.parseInt(listPG.resultList.get(j).getText());
                if (priceRange.contains("Under")) {
                    Assert.assertTrue(wholePrice > price, "Product-"+ j +" price(" + wholePrice + ") is under " + price);
                } else if (priceRange.contains("Above")) {
                    Assert.assertTrue(wholePrice > price, "Product-"+ j +" price(" + wholePrice + ") is under " + price);
                } else {
                    Assert.assertTrue(wholePrice > listPG.Min(priceRange) && wholePrice < listPG.Max(priceRange)
                            , "Product-" + j + " price(" + wholePrice + ") is not between " + listPG.Min(priceRange) + " and " + listPG.Max(priceRange));
                }
            }
            goBack();
        }
    }

    @Test
    public void verifySortedPrice() {
        HomePage homepage = new HomePage(webDriver);
        ListedBySortedPage sortedPage = new ListedBySortedPage(webDriver);
        homepage.open().clickCategory();

        // filter by price range
        sortedPage.filterByPrice();
        // sort from "low" to "high"
        sortedPage.sort();
        // verify sorted price
        List<WebElement> resultList = sortedPage.sortedProducts;

        for (int i = 0; i < resultList.size() - 1; i++) {
            int currentPrice = Integer.parseInt(resultList.get(i).getText());
            int nextPrice = Integer.parseInt(resultList.get(i + 1).getText());

            Assert.assertTrue(currentPrice <= nextPrice, "unordered price-" + resultList.get(i + 1).getText());
        }
    }
}
