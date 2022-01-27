package tests;

import base.BaseTest;
import io.qameta.allure.Issue;
import models.OrderModel;
import org.testng.annotations.Test;

import static data.TestData.*;
import static issues.DefectHandler.skipTestIfDefectIsOpen;
import static issues.Defects.DEFECT23;
import static assertions.CustomAssertions.assertEquals;

/**
 * A collection of tests that check basic product/s functionalities
 */
public final class ProductsTests extends BaseTest {

    private OrderModel order;

    @Test
    public void shouldCheckProductsCurrency() {
        assertEquals(prestaShop.openPrestaShop().enterRandomPopularProduct().getProductCurrency(), US_CURRENCY);
    }

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
                               .addRandomPopularProducts(order, 3)
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
                               .addRandomProducts(order, 3)
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
    public void shouldCheckOrderPriceAfterProductDeletion() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToClothesSection()
                               .goToProduct(TEST_PRODUCT_NAME)
                               .addProductToCart(order)
                               .increaseQuantityBy(2)
                               .getTopMenuPage()
                               .goToRandomProductsSection()
                               .addRandomProducts(order, 2)
                               .getTopMenuPage()
                               .goToCart()
                               .deleteItemFromCart(order, TEST_PRODUCT_NAME)
                               .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Issue("https://github.com/matmuz/Automation/issues/23")
    @Test
    public void shouldFindSearchedProduct() {

        skipTestIfDefectIsOpen(DEFECT23);

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