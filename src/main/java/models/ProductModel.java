package models;

/**
 * Class responsible for providing a product model (in PrestaShop) that gathers product's details
 */
public final class ProductModel {

    private final String productName;
    private final double productPrice;
    private final int quantity;

    public ProductModel(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
