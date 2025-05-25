package Models;

import java.time.LocalDate;

public class Product {
    int productId;
    String productName;
    double singleDeliveryFee;
    ProductCategory category;
    LocalDate expirationDate;
    double productPrice;
    double quantity;
    double originalQuantity;

    public Product (int productId, String productName, double singleDeliveryFee,
                    ProductCategory category, LocalDate expirationDate, double productPrice, double quantity){
        this.productId = productId;
        this.productName = productName;
        this.singleDeliveryFee = singleDeliveryFee;
        this.category = category;
        this.expirationDate = expirationDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.originalQuantity = quantity;
    }

    public double getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(double originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setSingleDeliveryFee(double singleDeliveryFee) {
        this.singleDeliveryFee = singleDeliveryFee;
    }
    public double getSingleDeliveryFee() {
        return singleDeliveryFee;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
    public ProductCategory getCategory() {
        return category;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public double getQuantity() {
        return quantity;
    }
}
