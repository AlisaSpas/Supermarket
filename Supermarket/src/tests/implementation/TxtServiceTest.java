package tests.implementation;

import Models.Receipt;
import Models.SoldProduct;
import Service.Implementation.TxtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TxtServiceTest {
    private final String baseDir = "./receiptsTxt";

    @AfterEach
    void cleanup() throws IOException {
        Path dir = Paths.get(baseDir);
        if (Files.exists(dir)) {
            Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void testWriteTxtFile() throws IOException {
        ArrayList<SoldProduct> sold = new ArrayList<>();
        sold.add(new SoldProduct("Bar", 3, 2.0, 5.0));
        LocalDateTime now = LocalDateTime.of(2025, 5, 25, 14, 30);
        Receipt receipt = new Receipt(8, 4, now, 10.0, sold, 7);

        TxtService svc = new TxtService();
        svc.writeTxtFile(receipt);

        String filename = baseDir + "/7_8.txt";
        assertTrue(Files.exists(Paths.get(filename)));

        String content = Files.readString(Paths.get(filename));
        assertTrue(content.contains("Product name: Bar"));
        assertTrue(content.contains("quantity: 5.0"));
        assertTrue(content.contains("price: 2.0"));
        assertTrue(content.contains("Total: 10.0"));
        assertTrue(content.contains("Time of sale: " + now.toString()));
    }
}
