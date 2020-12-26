package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.annotations.Test;

public class Order extends BaseTest {

    @Test(priority = 1)
    public void orderPrice() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .enterRandomPopularProduct()
                                  .addProductToCart(order)
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(order, 5)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test(priority = 2)
    public void orderItemsQuantity() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .goToRandomProduct()
                                  .increaseQuantityBy(3)
                                  .addProductToCart(order)
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .goToRandomProduct()
                                  .increaseQuantityBy(5)
                                  .decreaseQuantityBy(3)
                                  .addProductToCart(order)
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .goToRandomProduct()
                                  .decreaseQuantityBy(10)
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(order, 3)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test(priority = 3)
    public void orderItemsQuantityNegativeCase() {

        OrderModel order = new OrderModel();
        test.assertNotEquals(prestaShop.openPrestaShop()
                                     .getTopMenuPage()
                                     .goToSignInSection()
                                     .logIn(testUser.getEmail(), testUser.getPassword())
                                     .getTopMenuPage()
                                     .goToRandomProductsSection()
                                     .addRandomProducts(order, 3)
                                     .getTopMenuPage()
                                     .goToCart()
                                     .getItemsQuantity(), 5);
    }

    @Test(priority = 4)
    public void placeOrderAsGuest() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(5)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .proceedToCheckout()
                                  .placeOrderAsGuest(guestUser.getFirstName(), guestUser.getLastName(), guestUser.getEmail(), guestUser.getAddress(), guestUser.getCity(), guestUser.getState(), guestUser.getPostalCode(), guestUser.getCountry())
                                  .getConfirmationMessage(), testData.getConfirmationMessage());
    }

    @Test(priority = 5)
    public void placeOrderAsLoggedUser() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(5)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .proceedToCheckout()
                                  .placeOrderAsLoggedUser(testUser.getAddress(), testUser.getCity(), testUser.getState(), testUser.getPostalCode(), testUser.getCountry())
                                  .getConfirmationMessage(), testData.getConfirmationMessage());
    }

    @Test(priority = 6)
    public void orderHistory() {

        OrderModel order = new OrderModel();
        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .goToRandomProductsSection()
                                  .addRandomProducts(5)
                                  .getTopMenuPage()
                                  .goToCart()
                                  .proceedToCheckout()
                                  .placeOrderAsLoggedUser()
                                  .getOrderReference(order)
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .goToOrderHistory()
                                  .findOrderNumber(order.getOrderReferenceNumber()), order.getOrderReferenceNumber());
    }
}