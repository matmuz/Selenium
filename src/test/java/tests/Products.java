package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.annotations.Test;

import static issues.DefectHandler.skipTestIfDefectIsOpen;
import static issues.Defects.DEFECT_3;
import static tests.TestMethods.assertEquals;

/**
 * A collection of tests that check basic product/s functionalities
 */

public class Products extends BaseTest {

    /**
     * Test constants (data) that are used in tests
     */

    private static final String TEST_PRODUCT_NAME = "HUMMINGBIRD T-SHIRT";
    private static final String CUSTOMIZABLE_PRODUCT_NAME = "CUSTOMIZABLE MUG";

    /**
     * OrderModel instance that is instantiated during tests to verify the UI data against the data stored while testing
     */

    private OrderModel order;

    @Test
    public void shouldAddCustomizableProduct() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToAccessoriesSection()
                             .goToProduct(CUSTOMIZABLE_PRODUCT_NAME)
                             .addProductToCart()
                             .getProductName(), CUSTOMIZABLE_PRODUCT_NAME);
    }

    @Test
    public void shouldAddPopularProductsToCart() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                             .addRandomPopularProducts(order, 4)
                             .getTopMenuPage()
                             .goToCart()
                             .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void shouldAddRandomProductsToCart() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToRandomProductsSection()
                             .addRandomProducts(order, 6)
                             .getTopMenuPage()
                             .goToCart()
                             .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void shouldAddSpecificProductToCart() {

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
    public void shouldDeleteProductFromCart() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToClothesSection()
                             .goToProduct(TEST_PRODUCT_NAME)
                             .addProductToCart(order)
                             .getTopMenuPage()
                             .goToCart()
                             .deleteItemFromCart(order, TEST_PRODUCT_NAME)
                             .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void shouldFindSearchedProduct() {

        skipTestIfDefectIsOpen(DEFECT_3);

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