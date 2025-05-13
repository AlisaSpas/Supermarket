import java.time.DateTimeException;
import java.time.LocalDate;

public class Product {
    int productId;
    String productName;
    double singleDeliveryFee;
    ProductCategory category;
    LocalDate expirationDate;
    double productPrice;

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

    public void setProductPrice(double productPrice, double percent) {
        this.productPrice = productPrice + (productPrice * percent);
    }
    public double getProductPrice() {
        return productPrice;
    }
}
