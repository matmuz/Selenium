package models;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    private final List<ProductModel> PRODUCTS_IN_ORDER;
    private String orderReferenceNumber;

    public OrderModel() {
        PRODUCTS_IN_ORDER = new ArrayList<>();
    }

    public double getOrderItemsPrice() {
        double orderItemsPrice = 0;
        for (ProductModel productModel : PRODUCTS_IN_ORDER) {
            orderItemsPrice += productModel.getProductPrice() * productModel.getQuantity();
        }
        return Math.round(orderItemsPrice * 100.0) / 100.0;
    }

    public int getOrderItemsQuantity() {
        int orderItemsQuantity = 0;
        for (ProductModel productModel : PRODUCTS_IN_ORDER) {
            orderItemsQuantity += productModel.getQuantity();
        }
        return orderItemsQuantity;
    }

    public void addProductToList(ProductModel productModel) {
        this.PRODUCTS_IN_ORDER.add(productModel);
    }

    public void deleteProductFromList(String productName) {
        for (int i = 0; i < PRODUCTS_IN_ORDER.size(); i++) {
            if (PRODUCTS_IN_ORDER.get(i)
                    .getProductName()
                    .contains(productName)) {
                PRODUCTS_IN_ORDER.remove(i);
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