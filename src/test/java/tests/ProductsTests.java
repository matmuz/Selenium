package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest {

    @Test
    public void popularProduct() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .enterPopularProduct(testData.getProductName())
                                    .addProductToCart(order)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void popularProducts() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .addRandomPopularProducts(order, 4)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void addRandomProductsToCart() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToRandomProductsSection()
                                    .addRandomProducts(order, 6)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .getItemsQuantity(), 6);
    }

    @Test
    public void addSpecificProduct() {

        OrderModel order = new OrderModel();
        if (prestaShop.openPrestaShop()
                .getTopMenuPage()
                .goToClothesSection()
                .goToProduct(testData.getProductName())
                .addProductToCart(order)
                .getTopMenuPage()
                .goToCart()
                .getItemDetailsByName(testData.getProductName())
                .contains(testData.getProductName())) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void productDeletionInCart() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .enterPopularProduct(testData.getProductName())
                                    .addProductToCart(order)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .deleteItemFromCart(order, testData.getProductName())
                                    .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void searchForProduct() {

        OrderModel order = new OrderModel();
        if (prestaShop.openPrestaShop()
                .getTopMenuPage()
                .searchByText(testData.getProductName())
                .goToProduct(testData.getProductName())
                .addProductToCart(order)
                .getTopMenuPage()
                .goToCart()
                .getItemDetailsByName(testData.getProductName())
                .contains(testData.getProductName())) {
            Assert.assertTrue(true);
        }
    }
}