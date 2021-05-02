package tests;

import base.BaseTest;
import base.TestMethods;
import models.OrderModel;
import org.testng.annotations.Test;

public class Products extends BaseTest {

    @Test
    public void customisableProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(testData.getCustomizableProductName())
                                         .goToProduct(testData.getCustomizableProductName())
                                         .addProductToCart()
                                         .getProductName(), testData.getCustomizableProductName());
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
                                         .goToProduct(testData.getProductName())
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(testData.getProductName()), testData.getProductName());
    }

    @Test
    public void productDeletionInCart() {

        OrderModel order = new OrderModel();
        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(testData.getProductName())
                                         .goToProduct(testData.getProductName())
                                         .addProductToCart(order)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .deleteItemFromCart(order, testData.getProductName())
                                         .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void searchForProduct() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .searchByText(testData.getProductName())
                                         .goToProduct(testData.getProductName())
                                         .addProductToCart()
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemDetailsByName(testData.getProductName()), testData.getProductName());
    }
}