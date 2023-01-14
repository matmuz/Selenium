package models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class responsible for providing a model of an order (in PrestaShop) that gathers all the added to cart products info and stores order number (if an order is placed)
 */
public final class OrderModel {

    private List<ProductModel> productsInOrder;
    private String orderReferenceNumber;

    /**
     * Creates order model and an empty list as a container for products
     */
    public OrderModel() {
        productsInOrder = new ArrayList<>();
    }

    /**
     * Gets items price in order
     *
     * @return items price in order as a double
     */
    public double getOrderItemsPrice() {
        return calculate().apply(productsInOrder.stream()
                                                .mapToDouble(ProductModel::getProductPrice)
                                                .sum());
    }

    /**
     * Gets items quantity in order
     *
     * @return items quantity in order as int
     */
    public int getOrderItemsQuantity() {
        return productsInOrder.stream()
                              .mapToInt(ProductModel::getQuantity)
                              .sum();
    }

    /**
     * Adds products models to list
     *
     * @param productModel product model to add to list
     */
    public void addProductToList(ProductModel productModel) {
        this.productsInOrder.add(productModel);
    }

    /**
     * Deletes products models from list
     *
     * @param productName product name of a product model to remove from the list
     */
    public void deleteProductFromList(String productName) {
        productsInOrder = productsInOrder.stream()
                                         .filter(productModel -> !productModel.getProductName().equals(productName))
                                         .collect(Collectors.toList());
    }

    /**
     * Sets order reference number
     *
     * @param orderReferenceNumber number of an order placed in the shop
     */
    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    /**
     * Gets order reference number if previously set
     *
     * @return order reference number as String
     */
    public String getOrderReferenceNumber() {
        return this.orderReferenceNumber;
    }

    private Function<Double, Double> calculate() {
        return input -> Math.round(input * 100.0) / 100.0;
    }
}