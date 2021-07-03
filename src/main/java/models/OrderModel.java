package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for providing a model of an order (in PrestaShop) that gathers all the added to cart products info and stores order number (if an order is placed)
 */
public class OrderModel {

    private final List<ProductModel> productsInOrder;
    private String orderReferenceNumber;

    public OrderModel() {
        productsInOrder = new ArrayList<>();
    }

    public double getOrderItemsPrice() {
        double orderItemsPrice = 0;
        for (ProductModel productModel : productsInOrder) {
            orderItemsPrice += productModel.getProductPrice() * productModel.getQuantity();
        }
        return Math.round(orderItemsPrice * 100.0) / 100.0;
    }

    public int getOrderItemsQuantity() {
        int orderItemsQuantity = 0;
        for (ProductModel productModel : productsInOrder) {
            orderItemsQuantity += productModel.getQuantity();
        }
        return orderItemsQuantity;
    }

    public void addProductToList(ProductModel productModel) {
        this.productsInOrder.add(productModel);
    }

    public void deleteProductFromList(String productName) {
        for (int i = 0; i < productsInOrder.size(); i++) {
            if (productsInOrder.get(i)
                    .getProductName()
                    .contains(productName)) {
                productsInOrder.remove(i);
                break;
            }
        }
    }

    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    public String getOrderReferenceNumber() {
        return this.orderReferenceNumber;
    }
}