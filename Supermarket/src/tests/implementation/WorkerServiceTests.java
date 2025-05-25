package tests.implementation;

import Models.Worker;
import Service.Implementation.WorkerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WorkerServiceTests {
    @Test
    void testCreateWorker() {
        WorkerService svc = new WorkerService();
        Worker w = svc.createWorker("Charlie", 77, 3000.0);

        assertNotNull(w);
        assertEquals("Charlie", w.getWorkerName());
        assertEquals(77, w.getWorkerId());
        assertEquals(3000.0, w.getMonthlySalary());
    }
}
