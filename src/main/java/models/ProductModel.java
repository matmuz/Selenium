package models;

/**
 * Class responsible for providing a product model (in PrestaShop) that gathers product's details
 */
public final class ProductModel {

    private final String productName;
    private final double productPrice;
    private final int quantity;

    /**
     * Creates product model instance
     *
     * @param productName  name of the product
     * @param productPrice price of the product
     * @param quantity     quantity of the product
     */
    public ProductModel(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    /**
     * Gets product name
     *
     * @return product name as a String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets product price
     *
     * @return product price as a double
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Gets product quantity
     *
     * @return product quantity as an int
     */
    public int getQuantity() {
        return quantity;
    }
}
