package Service.Implementation;

import Models.CartItem;
import Models.Product;
import Models.Store;
import Service.Interface.ICartService;

public class CartService implements ICartService {
    @Override
    public CartItem createCartItem(Store store, int productId, double quantity) {
       Product product = store.getProducts().stream().filter(p->p.getProductId()==productId).findFirst().orElse(null);
       if(product == null){
           System.out.println("The product was not found.");
           return null;
       }
       double price = product.getProductPrice();
        CartItem cartItem = new CartItem(productId, quantity, price);
        return cartItem;
    }
}
