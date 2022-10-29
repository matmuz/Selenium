package tests;

import base.BaseTest;
import io.qameta.allure.Issue;
import models.OrderModel;
import org.testng.annotations.Test;

import static assertions.CustomAssertions.*;
import static data.ExistingUser.EMAIL;
import static data.ExistingUser.PASSWORD;
import static issues.DefectHandler.skipTestIfDefectIsOpen;
import static issues.Defects.DEFECT22;
import static pages.cart.CheckoutPage.CONFIRMATION_MESSAGE;

/**
 * A collection of tests that check basic cart functionalities
 */
public final class OrdersTest extends BaseTest {

    private OrderModel order;

    @Issue("https://github.com/matmuz/Automation/issues/22")
    @Test
    public void shouldCheckOrderPrice() {

        skipTestIfDefectIsOpen(DEFECT22);

        order = new OrderModel();

        assertEquals(prestaShop.openPrestaShop()
                               .enterRandomPopularProduct()
                               .addProductToCart(order)
                               .getTopMenuPage()
                               .goToRandomProductsSection()
                               .addRandomProducts(order, 3)
                               .getTopMenuPage()
                               .goToCart()
                               .getItemsPrice(), order.getOrderItemsPrice());
    }

    @Test
    public void shouldCheckItemsQuantity() {

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
                               .addRandomProducts(order, 1)
                               .getTopMenuPage()
                               .goToCart()
                               .getItemsQuantity(), order.getOrderItemsQuantity());
    }

    @Test
    public void shouldCheckNotEqualItemsQuantity() {

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
    public void shouldPlaceOrderAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToRandomProductsSection()
                               .addRandomProducts(3)
                               .getTopMenuPage()
                               .goToCart()
                               .proceedToCheckout()
                               .placeOrderAsGuest(guestUser.getFirstName(), guestUser.getLastName(),
                                                  guestUser.getEmail(), guestUser.getAddress(), guestUser.getCity(),
                                                  guestUser.getPostalCode(), guestUser.getCountry())
                               .getConfirmationMessage(), CONFIRMATION_MESSAGE);
    }

    @Test
    public void shouldPlaceOrderAsLoggedUser() {

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToSignInSection()
                               .logIn(EMAIL, PASSWORD)
                               .getTopMenuPage()
                               .goToRandomProductsSection()
                               .addRandomProducts(3)
                               .getTopMenuPage()
                               .goToCart()
                               .proceedToCheckout()
                               .placeOrderAsLoggedUser()
                               .getConfirmationMessage(), CONFIRMATION_MESSAGE);
    }

    @Test
    public void shouldStoreOrderNumber() {

        order = new OrderModel();

        assertTrue(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(EMAIL, PASSWORD)
                                         .getTopMenuPage()
                                         .goToRandomProductsSection()
                                         .addRandomProducts(3)
                                         .getTopMenuPage()
                                         .goToCart()
                                         .proceedToCheckout()
                                         .placeOrderAsLoggedUser()
                                         .setOrderReferenceNumberOfAnOrderToAModel(order)
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .goToOrderHistory()
                                         .findOrderNumber(order.getOrderReferenceNumber()));
    }
}