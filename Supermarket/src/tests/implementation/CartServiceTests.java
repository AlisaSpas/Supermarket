package tests.implementation;

import Models.CartItem;
import Models.Product;
import Models.ProductCategory;
import Models.Store;
import Service.Implementation.CartService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTests {
    @Test
    void testCreateCartItemWhenProductExists() {
        Store store = new Store(1, 0, 0, 0, 0);
        Product p = new Product(42, "Test", 0, ProductCategory.FOOD, LocalDate.now(), 2.0, 100);
        store.addProducts(p);

        CartService svc = new CartService();
        CartItem item = svc.createCartItem(store, 42, 3.5);

        assertNotNull(item);
        assertEquals(42, item.getProductId());
        assertEquals(3.5, item.getQuantity());
        assertEquals(2.0, item.getProductPrice());
    }

    @Test
    void testCreateCartItemWhenProductDoesNotExist() {
        Store store = new Store(1, 0, 0, 0, 0);
        CartService svc = new CartService();
        assertNull(svc.createCartItem(store, 99, 1.0));
    }
}