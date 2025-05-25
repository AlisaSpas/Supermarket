package Service.Interface;

import Models.CartItem;
import Models.Product;
import Models.Store;

public interface ICartService {
    public CartItem createCartItem(Store store, int productId, double quantity);
}
