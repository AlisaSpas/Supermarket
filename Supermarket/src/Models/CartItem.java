package Models;

public class CartItem {
    int productId;
    double quantity;
    double productPrice;

    public CartItem(int productId, double quantity, double productPrice){
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getQuantity() {
        return quantity;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public double getProductPrice() {
        return productPrice;
    }
}
