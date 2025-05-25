package tests.implementation;

import Models.Product;
import Models.ProductCategory;
import Models.Store;
import Service.Implementation.SerializationService;
import Service.Implementation.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreServiceTest {
    private StoreService svc;
    private Store store;

    @BeforeEach
    void setUp() {
        svc = new StoreService(new SerializationService());
        store = svc.createStore(1, 10.0, 20.0, 5, 2.0);
    }

    @Test
    void testCreateStore() {
        assertEquals(1, store.getId());
        assertEquals(10.0, store.getOverpriceFood());
        assertEquals(20.0, store.getOverpriceNonfood());
    }

    @Test
    void testAddProductsFood() {
        Product p = new Product(1, "Apple", 0, ProductCategory.FOOD, LocalDate.now(), 100.0, 10.0);
        svc.addProducts(store, p);
        assertEquals(110.0, p.getProductPrice(), 1e-6);
        assertTrue(store.getProducts().contains(p));
    }

    @Test
    void testAddProductsNonFood() {
        Product p = new Product(2, "Brush", 0, ProductCategory.NONFOOD, LocalDate.now(), 200.0, 5.0);
        svc.addProducts(store, p);
        assertEquals(240.0, p.getProductPrice(), 1e-6);
        assertTrue(store.getProducts().contains(p));
    }
}
