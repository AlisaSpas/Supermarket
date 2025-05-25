package tests.models;

import Models.Worker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkerTests {
    @Test
    void testGettersAndSetters() {
        Worker w = new Worker("Alice", 55, 2000.0);
        assertEquals("Alice", w.getWorkerName());
        assertEquals(55, w.getWorkerId());
        assertEquals(2000.0, w.getMonthlySalary());

        w.setWorkerName("Bob");
        w.setWorkerId(66);
        w.setMonthlySalary(2500.0);
        w.setCashRegisterId(5);

        assertEquals("Bob", w.getWorkerName());
        assertEquals(66, w.getWorkerId());
        assertEquals(2500.0, w.getMonthlySalary());
        assertEquals(5, w.getCashRegisterId());
    }
}
