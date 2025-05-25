package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class SoldProduct implements Serializable {
    String productName;
    int productId;
    double productPrice;
    double quantity;

    public SoldProduct(String productName, int productId, double productPrice, double quantity){
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getProductId() {
        return productId;
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
