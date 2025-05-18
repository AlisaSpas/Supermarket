package Models;

import java.util.ArrayList;

public class Cart {
    int cartId;
    ArrayList<CartItem> items;
    double customerMoney;

    public Cart(int cartId, double customerMoney){
        this.cartId = cartId;
        this.customerMoney = customerMoney;
        this.items = new ArrayList<>();
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public int getCartId() {
        return cartId;
    }

    public void addItems(CartItem cartItem) {
        this.items.add(cartItem);
    }
    public ArrayList<CartItem> getItems() {
        return items;
    }


}
