package tests.implementation;

import Models.Product;
import Models.ProductCategory;
import Service.Implementation.ProductService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTests {
    @Test
    void testCreateProduct() {
        LocalDate exp = LocalDate.of(2025, 1, 1);
        ProductService svc = new ProductService();
        Product p = svc.createProduct(10, "Soap", 1.0, ProductCategory.NONFOOD, exp, 3.0, 50.0);

        assertNotNull(p);
        assertEquals(10, p.getProductId());
        assertEquals("Soap", p.getProductName());
        assertEquals(1.0, p.getSingleDeliveryFee());
        assertEquals(ProductCategory.NONFOOD, p.getCategory());
        assertEquals(exp, p.getExpirationDate());
        assertEquals(3.0, p.getProductPrice());
        assertEquals(50.0, p.getQuantity());
    }
}
