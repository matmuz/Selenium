package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.annotations.Test;

import static data.ExistingUser.EMAIL;
import static data.ExistingUser.PASSWORD;
import static pages.cart.CheckoutPage.CONFIRMATION_MESSAGE;
import static tests.TestMethods.assertEquals;
import static tests.TestMethods.assertNotEquals;

public class Order extends BaseTest {

    private OrderModel order;

    @Test
    public void orderPrice() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                                         .enterRandomPopularProduct()
                                         .addProductToCart(order)
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(order, 5)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void orderItemsQuantity() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
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

    @Test
    public void orderItemsQuantityNegativeCase() {

        order = new OrderModel();

        assertNotEquals(prestaShop.openPrestaShop()
                                            .getTopMenuPage()
                                            .goToSignInSection()
                                            .logIn(EMAIL, PASSWORD)
                                            .getTopMenuPage()
                                            .goToRandomProductsSection()
                                            .addRandomProducts(order, 3)
                                            .getTopMenuPage()
                                            .goToCart()
                                            .getItemsQuantity(), 5);
    }

    @Test
    public void placeOrderAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(5)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .proceedToCheckout()
                                         .placeOrderAsGuest(guestUser.getFirstName(), guestUser.getLastName(), guestUser.getEmail(), guestUser.getAddress(), guestUser.getCity(), guestUser.getPostalCode())
                                         .getConfirmationMessage(), CONFIRMATION_MESSAGE);
    }

    @Test
    public void placeOrderAsLoggedUser() {

        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(EMAIL, PASSWORD)
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(5)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .proceedToCheckout()
                                         .placeOrderAsLoggedUser()
                                         .getConfirmationMessage(), CONFIRMATION_MESSAGE);
    }

    @Test
    public void orderHistory() {

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(EMAIL, PASSWORD)
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