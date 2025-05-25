package tests.models;

import Models.Cart;
import Models.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests {
    @Test
    void testCartInitialization() {
        Cart cart = new Cart(1, 100.0);
        assertEquals(1, cart.getCartId());
        assertEquals(100.0, cart.getCustomerMoney());
        assertTrue(cart.getItems().isEmpty(), "New cart should have no items");
    }

    @Test
    void testAddItems() {
        Cart cart = new Cart(2, 50.0);
        CartItem item = new CartItem(42, 3.5, 7.25);
        cart.addItems(item);
        assertEquals(1, cart.getItems().size());
        assertSame(item, cart.getItems().get(0));
    }

    @Test
    void testGettersAndSetters() {
        CartItem item = new CartItem(5, 2.0, 10.0);
        assertEquals(5, item.getProductId());
        assertEquals(2.0, item.getQuantity());
        assertEquals(10.0, item.getProductPrice());

        item.setProductId(7);
        item.setQuantity(4.0);
        item.setProductPrice(12.5);

        assertEquals(7, item.getProductId());
        assertEquals(4.0, item.getQuantity());
        assertEquals(12.5, item.getProductPrice());
    }
}
