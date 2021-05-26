package tests;

import base.BaseTest;
import data.TestData;
import models.OrderModel;
import org.testng.annotations.Test;

public class Products extends BaseTest {

    @Test
    public void customisableProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TestData.CUSTOMIZABLE_PRODUCT_NAME)
                                         .goToProduct(TestData.CUSTOMIZABLE_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getProductName(), TestData.CUSTOMIZABLE_PRODUCT_NAME);
    }

    @Test
    public void popularProducts() {

        OrderModel order = new OrderModel();
        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .addRandomPopularProducts(order, 4)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void addRandomProductsToCart() {

        OrderModel order = new OrderModel();
        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(order, 6)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void addSpecificProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToClothesSection()
                                         .goToProduct(TestData.TEST_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(TestData.TEST_PRODUCT_NAME), TestData.TEST_PRODUCT_NAME);
    }

    @Test
    public void productDeletionInCart() {

        OrderModel order = new OrderModel();
        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TestData.TEST_PRODUCT_NAME)
                                         .goToProduct(TestData.TEST_PRODUCT_NAME)
                                         .addProductToCart(order)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .deleteItemFromCart(order, TestData.TEST_PRODUCT_NAME)
                                         .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void searchForProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TestData.TEST_PRODUCT_NAME)
                                         .goToProduct(TestData.TEST_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(TestData.TEST_PRODUCT_NAME), TestData.TEST_PRODUCT_NAME);
    }
}