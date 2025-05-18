import Models.*;
import Service.Implementation.*;
import Service.Interface.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        IWorkerService workerService = new WorkerService();
        IStoreService storeService = new StoreService();
        Store store = storeService.createStore(12,15,24,22);

        IProductService productService = new ProductService();
        Product product1 = productService.createProduct(1,"Sugar",12.80,
                ProductCategory.FOOD, LocalDate.now().plusMonths(6),3);
        Product product2 = productService.createProduct(2,"Chair", 4,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(7),17);

        Worker worker1 = workerService.createWorker("Ivan Ivanov", 1, 1000);
        Worker worker2 = workerService.createWorker("Stanislav Georgiev", 2, 980);

        ICashRegisterService cashRegisterService = new CashRegisterService();
        CashRegister register1 = cashRegisterService.createCashRegister(1);
        register1.setWorkerId(worker1.getWorkerId());

        storeService.addWorkers(store, worker1);
        storeService.addWorkers(store, worker2);
        storeService.addProducts(store, product1);
        storeService.addProducts(store, product2);
        storeService.addCashRegisters(store,register1);

        storeService.printProducts(store);
        storeService.printWorkers(store);

        double customerMoney = 100;

        Cart cart1 = new Cart(1,customerMoney);
        CartItem item1 = new CartItem(1,4,12);

        cart1.addItems(item1);
        storeService.printCart(store,cart1);

        Receipt receipt1 = cashRegisterService.checkoutCustomer(1, LocalDateTime.now(),cart1,store,register1);
        IReceiptService receiptService = new ReceiptService();
        receiptService.printReceipt(receipt1);
        //TODO all validations. save receipts in txt files. Subtract quantity of products when sold. Create all reports for stores.
    }
}