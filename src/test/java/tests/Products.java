package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.annotations.Test;

import static tests.TestMethods.assertEquals;

public class Products extends BaseTest {

    private static final String TEST_PRODUCT_NAME = "HUMMINGBIRD PRINTED T-SHIRT";
    private static final String CUSTOMIZABLE_PRODUCT_NAME = "CUSTOMIZABLE MUG";

    @Test
    public void customisableProduct() {

        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(CUSTOMIZABLE_PRODUCT_NAME)
                                         .goToProduct(CUSTOMIZABLE_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getProductName(), CUSTOMIZABLE_PRODUCT_NAME);
    }

    @Test
    public void popularProducts() {

        OrderModel order = new OrderModel();
        assertEquals(prestaShop.openPrestaShop()
                                         .addRandomPopularProducts(order, 4)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void addRandomProductsToCart() {

        OrderModel order = new OrderModel();
        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(order, 6)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void addSpecificProduct() {

        assertEquals(prestaShop.openPrestaShop()
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
        assertEquals(prestaShop.openPrestaShop()
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

        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(TEST_PRODUCT_NAME)
                                         .goToProduct(TEST_PRODUCT_NAME)
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(TEST_PRODUCT_NAME), TEST_PRODUCT_NAME);
    }
}