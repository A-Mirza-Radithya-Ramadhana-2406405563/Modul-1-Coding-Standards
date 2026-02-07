package id.ac.ui.cs.advprog.eshop.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super("Product not found with id: " + productId);
    }
}
