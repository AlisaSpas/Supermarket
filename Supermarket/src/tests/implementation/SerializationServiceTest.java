package tests.implementation;

import Models.Receipt;
import Models.SoldProduct;
import Service.Implementation.SerializationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerializationServiceTest {
    private final String dir = "./receipts";

    @AfterEach
    void cleanup() {
        // delete all .ser files under receipts
        File d = new File(dir);
        if (d.exists() && d.isDirectory()) {
            for (File f : d.listFiles((_, name) -> name.endsWith(".ser"))) {
                f.delete();
            }
        }
    }

    @Test
    void testSerializationAndDeserialization() throws Exception {
        ArrayList<SoldProduct> sold = new ArrayList<>();
        sold.add(new SoldProduct("X", 1, 1.0, 1.0));
        LocalDateTime now = LocalDateTime.of(2025, 5, 25, 16, 0);
        Receipt receipt = new Receipt(9, 5, now, 5.0, sold, 11);

        SerializationService svc = new SerializationService();
        svc.serialization(receipt);

        Receipt loaded = svc.deserialization("11_9");

        assertNotNull(loaded);
        assertEquals(9, loaded.getReceiptId());
        assertEquals(5, loaded.getWorkerId());
        assertEquals(now, loaded.getTimeOfSale());
        assertEquals(5.0, loaded.getTotal());
        assertEquals(11, loaded.getStoreId());
        assertEquals(1, loaded.getSoldProducts().size());
        assertEquals("X", loaded.getSoldProducts().get(0).getProductName());
    }
}
