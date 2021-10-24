package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for providing a model of an order (in PrestaShop) that gathers all the added to cart products info and stores order number (if an order is placed)
 */
public final class OrderModel {

    private List<ProductModel> productsInOrder;
    private String orderReferenceNumber;

    public OrderModel() {
        productsInOrder = new ArrayList<>();
    }

    public double getOrderItemsPrice() {
        return Math.round(productsInOrder.stream()
                                         .mapToDouble(ProductModel::getProductPrice)
                                         .sum() * 100.0) / 100.0;
    }

    public int getOrderItemsQuantity() {
        return productsInOrder.stream()
                              .mapToInt((ProductModel::getQuantity))
                              .sum();
    }

    public void addProductToList(ProductModel productModel) {
        this.productsInOrder.add(productModel);
    }

    public void deleteProductFromList(String productName) {
        productsInOrder = productsInOrder.stream()
                                         .filter(productModel -> !productModel.getProductName().equals(productName))
                                         .collect(Collectors.toList());
    }

    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    public String getOrderReferenceNumber() {
        return this.orderReferenceNumber;
    }
}