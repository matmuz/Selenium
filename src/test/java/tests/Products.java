package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.annotations.Test;

public class Products extends BaseTest {

    @Test(priority = 1)
    public void customisableProduct() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .searchByText(testData.getCustomizableProductName())
                                  .goToProduct(testData.getCustomizableProductName())
                                  .addProductToCart()
                                  .getProductName(), testData.getCustomizableProductName());
    }

    @Test(priority = 2)
    public void popularProducts() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .addRandomPopularProducts(order, 4)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test(priority = 3)
    public void addRandomProductsToCart() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(order, 6)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test(priority = 4)
    public void addSpecificProduct() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToClothesSection()
                                  .goToProduct(testData.getProductName())
                                  .addProductToCart()
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemDetailsByName(testData.getProductName()), testData.getProductName());
    }

    @Test(priority = 5)
    public void productDeletionInCart() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .searchByText(testData.getProductName())
                                  .goToProduct(testData.getProductName())
                                  .addProductToCart(order)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .deleteItemFromCart(order, testData.getProductName())
                                  .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test(priority = 6)
    public void searchForProduct() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .searchByText(testData.getProductName())
                                  .goToProduct(testData.getProductName())
                                  .addProductToCart()
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemDetailsByName(testData.getProductName()), testData.getProductName());
    }
}