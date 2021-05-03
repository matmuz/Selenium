package models;

public class ProductModel {

    private final String PRODUCT_NAME;
    private final double PRODUCT_PRICE;
    private final int QUANTITY;

    public ProductModel(String productName, double productPrice, int quantity) {
        PRODUCT_NAME = productName;
        PRODUCT_PRICE = productPrice;
        QUANTITY = quantity;
    }

    public String getProductName() {
        return PRODUCT_NAME;
    }

    public double getProductPrice() {
        return PRODUCT_PRICE;
    }

    public int getQuantity() {
        return QUANTITY;
    }
}
