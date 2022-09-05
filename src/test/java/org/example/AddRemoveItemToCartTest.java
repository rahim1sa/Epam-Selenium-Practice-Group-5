package org.example;

import org.example.pageObject.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddRemoveItemToCartTest extends BaseTest {

    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void addToCart() {

        ProductPage productPage = new ProductPage(webDriver);

        String cartAmount = productPage
                .open()
                .addToCart()
                .closeSlideSheet()
                .takeCartAmount();
        Assert.assertEquals("1", cartAmount, "Adding to cart failed");
    }

    @Test
    public void removeFromCart() {
        ProductPage productPage = new ProductPage(webDriver);

        String cartPrice = productPage
                .open()
                .addToCart()
                .closeSlideSheet()
                .cartClick()
                .cartClear()
                .getCartSubtotal();

        Assert.assertEquals(" $0.00", cartPrice, "Removing from cart failed");
    }
}
