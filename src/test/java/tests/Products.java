package tests;

import base.BaseTest;
import base.TestMethods;
import data.ITestData;
import models.OrderModel;
import org.testng.annotations.Test;

public class Products extends BaseTest implements ITestData {

    @Test
    public void customisableProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(CUSTOMIZABLE_PRODUCT_NAME)
                                         .goToProduct(CUSTOMIZABLE_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getProductName(), CUSTOMIZABLE_PRODUCT_NAME);
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
                                         .goToProduct(TEST_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(TEST_PRODUCT_NAME), TEST_PRODUCT_NAME);
    }

    @Test
    public void productDeletionInCart() {

        OrderModel order = new OrderModel();
        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TEST_PRODUCT_NAME)
                                         .goToProduct(TEST_PRODUCT_NAME)
                                         .addProductToCart(order)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .deleteItemFromCart(order, TEST_PRODUCT_NAME)
                                         .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void searchForProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TEST_PRODUCT_NAME)
                                         .goToProduct(TEST_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(TEST_PRODUCT_NAME), TEST_PRODUCT_NAME);
    }
}