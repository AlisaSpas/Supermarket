package tests.models;

import Models.Receipt;
import Models.SoldProduct;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ReceiptTests {
    @Test
    void testGettersAndSetters() {
        ArrayList<SoldProduct> sold = new ArrayList<>();
        sold.add(new SoldProduct("Candy", 1, 0.5, 10.0));
        LocalDateTime now = LocalDateTime.now();
        Receipt r = new Receipt(7, 99, now, 5.0, sold, 3);

        assertEquals(7, r.getReceiptId());
        assertEquals(99, r.getWorkerId());
        assertEquals(now, r.getTimeOfSale());
        assertEquals(5.0, r.getTotal());
        assertEquals(sold, r.getSoldProducts());
        assertEquals(3, r.getStoreId());

        ArrayList<SoldProduct> sold2 = new ArrayList<>();
        r.setSoldProducts(sold2);
        r.setTotal(2.5);
        assertSame(sold2, r.getSoldProducts());
        assertEquals(2.5, r.getTotal());
    }
}
