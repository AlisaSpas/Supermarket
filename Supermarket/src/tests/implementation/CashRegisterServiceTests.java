package tests.implementation;

import Exceptions.NoWorkerException;
import Models.*;
import Service.Implementation.CashRegisterService;
import Service.Implementation.SerializationService;
import Service.Implementation.StoreService;
import Service.Interface.ISerializationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterServiceTests {
    private CashRegisterService cashRegisterService;
    private StoreService storeService;
    private Store store;
    private CashRegister register;
    private Worker worker;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        new File("./receipts").mkdirs();

        cashRegisterService = new CashRegisterService();
        ISerializationService serializationService = new SerializationService();
        storeService = new StoreService(serializationService);

        store = storeService.createStore(
                1,
                0.0,
                0.0,
                10,
                20.0
        );

        register = new CashRegister(101);
        storeService.addCashRegisters(store, register);

        worker = new Worker("Alice", 501, 1500.0);
        storeService.addWorkers(store, worker);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void setWorkerToCashRegister_success() {
        cashRegisterService.setWorkerToCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );

        assertEquals(worker.getWorkerId(), register.getWorkerId());
        assertEquals(register.getNumber(), worker.getCashRegisterId());
        assertTrue(outContent.toString().isEmpty(),
                "no error should be printed on success");
    }

    @Test
    void setWorkerToCashRegister_registerNotFound() {
        cashRegisterService.setWorkerToCashRegister(
                worker.getWorkerId(), 999, store
        );
        assertEquals(
                "Cash register with number: 999 does not exist!",
                outContent.toString().trim()
        );
    }

    @Test
    void setWorkerToCashRegister_alreadyHasWorker() {

        register.setWorkerId(777);
        cashRegisterService.setWorkerToCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );
        assertEquals(
                "There is already a worker at cash register " + register.getNumber() + "!",
                outContent.toString().trim()
        );
    }

    @Test
    void setWorkerToCashRegister_workerNotFound() {
        cashRegisterService.setWorkerToCashRegister(
                888, register.getNumber(), store
        );
        assertEquals(
                "Worker with Id: 888 does not exist!",
                outContent.toString().trim()
        );
    }

    @Test
    void setWorkerToCashRegister_workerAlreadyAssignedElsewhere() {

        worker.setCashRegisterId(202);
        cashRegisterService.setWorkerToCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );
        assertEquals(
                "Worker already works at cash register " + worker.getCashRegisterId(),
                outContent.toString().trim()
        );
    }



    @Test
    void removeWorkerFromCashRegister_success() {

        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(register.getNumber());

        cashRegisterService.removeWorkerFromCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );

        assertEquals(0, register.getWorkerId());
        assertEquals(0, worker.getCashRegisterId());
        assertTrue(outContent.toString().isEmpty());
    }

    @Test
    void removeWorkerFromCashRegister_registerNotFound() {
        cashRegisterService.removeWorkerFromCashRegister(
                worker.getWorkerId(), 333, store
        );
        assertEquals(
                "Cash register with number: 333 does not exist!",
                outContent.toString().trim()
        );
    }

    @Test
    void removeWorkerFromCashRegister_noWorkerAssigned() {

        cashRegisterService.removeWorkerFromCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );
        assertEquals(
                "There is no worker at cash register " + register.getNumber() + "!",
                outContent.toString().trim()
        );
    }

    @Test
    void removeWorkerFromCashRegister_workerNotInStore() {

        register.setWorkerId(444);
        cashRegisterService.removeWorkerFromCashRegister(
                444, register.getNumber(), store
        );
        assertEquals(
                "Worker with Id: 444 does not exist!",
                outContent.toString().trim()
        );
    }

    @Test
    void removeWorkerFromCashRegister_workerMismatch() {

        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(505);
        cashRegisterService.removeWorkerFromCashRegister(
                worker.getWorkerId(), register.getNumber(), store
        );
        assertEquals(
                "Worker does not work at cash register " + register.getNumber(),
                outContent.toString().trim()
        );
    }



    @Test
    void checkoutCustomers_throwsWhenNoWorker() {
        assertThrows(
                NoWorkerException.class,
                () -> cashRegisterService.checkoutCustomers(
                        LocalDateTime.now(),
                        register.getCustomers(),
                        store,
                        register
                )
        );
    }

    @Test
    void checkoutCustomers_emptyQueueReturnsEmpty() throws NoWorkerException {

        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(register.getNumber());

        List<Receipt> receipts = cashRegisterService.checkoutCustomers(
                LocalDateTime.now(),
                register.getCustomers(),
                store,
                register
        );
        assertTrue(receipts.isEmpty());
    }

    @Test
    void checkoutCustomers_successfulSale() throws NoWorkerException {

        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(register.getNumber());


        Product p = new Product(
                10,
                "Banana",
                0.0,
                ProductCategory.FOOD,
                LocalDate.now().plusDays(5),
                5.0,
               50.0
        );
        storeService.addProducts(store, p);


        Cart cart = new Cart(7, 20.0);
        cart.addItems(new CartItem(10,3.0, 5.0));
        register.addCustomers(cart);

        List<Receipt> receipts = cashRegisterService.checkoutCustomers(
                LocalDateTime.now(),
                register.getCustomers(),
                store,
                register
        );
        assertEquals(1, receipts.size());

        Receipt r = receipts.get(0);
        assertEquals(1, r.getReceiptId(), "first receipt id should start at 1");
        assertEquals(worker.getWorkerId(), r.getWorkerId());
        assertEquals(15.0, r.getTotal());
        assertEquals(1, r.getSoldProducts().size());
    }

    @Test
    void checkoutCustomers_insufficientFunds() throws NoWorkerException {
        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(register.getNumber());


        Product p = new Product(
                20, "Apple", 0.0, ProductCategory.FOOD,
                LocalDate.now().plusDays(5),
                3.0, 20.0
        );
        storeService.addProducts(store, p);

        Cart cart = new Cart(2,  5.0);
        cart.addItems(new CartItem(20, 3.0, 3.0));
        register.addCustomers(cart);

        List<Receipt> receipts = cashRegisterService.checkoutCustomers(
                LocalDateTime.now(),
                register.getCustomers(),
                store,
                register
        );
        assertTrue(receipts.isEmpty());
        assertTrue(outContent.toString().contains("has insufficient funds!"));
    }

    @Test
    void checkoutCustomers_productExpiredIsLogged() throws NoWorkerException {
        register.setWorkerId(worker.getWorkerId());
        worker.setCashRegisterId(register.getNumber());

        Product p = new Product(
                30, "Yogurt", 0.0, ProductCategory.FOOD,
                LocalDate.now().minusDays(1),
                2.0, 10.0
        );
        storeService.addProducts(store, p);

        Cart cart = new Cart(3, 50.0);
        cart.addItems(new CartItem(30, 1.0, 2.0));
        register.addCustomers(cart);

        List<Receipt> receipts = cashRegisterService.checkoutCustomers(
                LocalDateTime.now(),
                register.getCustomers(),
                store,
                register
        );

        assertEquals(1, receipts.size());
        assertTrue(outContent.toString().contains("is expired. Expiration date:"));
    }
}
