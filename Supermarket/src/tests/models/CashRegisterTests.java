package tests.models;

import Models.Cart;
import Models.CashRegister;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTests {
    @Test
    void testInitializationAndCustomerQueue() {
        CashRegister cr = new CashRegister(99);
        assertEquals(99, cr.getNumber());
        assertTrue(cr.getCustomers().isEmpty());

        Cart c1 = new Cart(1, 20.0);
        Cart c2 = new Cart(2, 30.0);
        cr.addCustomers(c1);
        cr.addCustomers(c2);

        Queue<Cart> q = cr.getCustomers();
        assertEquals(2, q.size());
        assertSame(c1, q.peek());

        cr.pollCustomer();
        assertEquals(1, cr.getCustomers().size());
        assertSame(c2, cr.getCustomers().peek());
    }

    @Test
    void testWorkerIdSetterGetter() {
        CashRegister     cr = new CashRegister(5);
        cr.setWorkerId(123);
        assertEquals(123, cr.getWorkerId());
    }
}

