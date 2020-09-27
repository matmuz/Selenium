package tests;

import base.BaseTest;
import models.OrderModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTests extends BaseTest {

    @Test(priority = 1)
    public void orderPrice() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
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
        Assert.assertEquals(prestaShop.openPrestaShop()
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
    public void placeOrderAsGuest() {

        String confirmationMessage = testData.getConfirmationMessage();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToRandomProductsSection()
                                    .addRandomProducts(5)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .proceedToCheckout()
                                    .placeOrderAsGuest(guestData.getFirstName(), guestData.getLastName(), guestData.getEmail(), guestData.getAddress(), guestData.getCity(), guestData.getState(), guestData.getPostalCode(), guestData.getCountry())
                                    .getConfirmationMessage(), confirmationMessage);
    }

    @Test(priority = 4)
    public void placeOrderAsLoggedUser() {

        String confirmationMessage = testData.getConfirmationMessage();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .goToRandomProductsSection()
                                    .addRandomProducts(5)
                                    .getTopMenuPage()
                                    .goToCart()
                                    .proceedToCheckout()
                                    .placeOrderAsLoggedUser()
                                    .getConfirmationMessage(), confirmationMessage);
    }

    @Test(priority = 5)
    public void orderHistory() {

        OrderModel order = new OrderModel();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
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