package tests.implementation;

import Models.Receipt;
import Models.SoldProduct;
import Service.Implementation.ReceiptService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptServiceTest {
    @Test
    void testCreateAndPrintReceipt() {
        ArrayList<SoldProduct> sold = new ArrayList<>();
        sold.add(new SoldProduct("Gum", 5, 0.75, 2.0));
        LocalDateTime now = LocalDateTime.of(2025, 5, 25, 12, 0);

        ReceiptService svc = new ReceiptService();
        Receipt receipt = svc.createReceipt(1, 2, now, 1.5, sold, 3);

        assertNotNull(receipt);
        assertEquals(1, receipt.getReceiptId());
        assertEquals(2, receipt.getWorkerId());
        assertEquals(now, receipt.getTimeOfSale());
        assertEquals(1.5, receipt.getTotal());
        assertEquals(sold, receipt.getSoldProducts());
        assertEquals(3, receipt.getStoreId());

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));

        svc.printReceipt(receipt);

        System.setOut(old);
        String out = buf.toString();
        assertTrue(out.contains("Receipt id: 1"));
        assertTrue(out.contains("Worker id: 2"));
        assertTrue(out.contains("Product name: Gum"));
        assertTrue(out.contains("quantity: 2.0"));
        assertTrue(out.contains("price: 0.75"));
        assertTrue(out.contains("1.5"));
        assertTrue(out.contains(now.toString()));
    }
}
