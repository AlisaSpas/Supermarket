package tests.models;

import Models.Product;
import Models.ProductCategory;
import Models.SoldProduct;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTests {
    @Test
    void testGettersAndSetters() {
        LocalDate exp = LocalDate.of(2025, 12, 31);
        Product p = new Product(1, "Milk", 1.0, ProductCategory.FOOD, exp, 2.5, 100.0);

        assertEquals(1, p.getProductId());
        assertEquals("Milk", p.getProductName());
        assertEquals(1.0, p.getSingleDeliveryFee());
        assertEquals(ProductCategory.FOOD, p.getCategory());
        assertEquals(exp, p.getExpirationDate());
        assertEquals(2.5, p.getProductPrice());
        assertEquals(100.0, p.getQuantity());
        assertEquals(100.0, p.getOriginalQuantity());

        p.setProductPrice(3.0);
        p.setQuantity(80.0);

        assertEquals(3.0, p.getProductPrice());
        assertEquals(80.0, p.getQuantity());
    }

    @Test
    void testGettersAndSettersSoldProduct() {
        SoldProduct sp = new SoldProduct("Bread", 10, 1.5, 4.0);
        assertEquals("Bread", sp.getProductName());
        assertEquals(10, sp.getProductId());
        assertEquals(1.5, sp.getProductPrice());
        assertEquals(4.0, sp.getQuantity());

        sp.setQuantity(2.0);
        assertEquals(2.0, sp.getQuantity());
    }
}
