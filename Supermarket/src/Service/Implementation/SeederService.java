package Service.Implementation;

import Models.*;
import Service.Interface.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeederService implements ISeederService {
    @Override
    public ArrayList<Store> seedStores() {
        IStoreService storeService = new StoreService(new SerializationService());
        Store store1 = storeService.createStore(1,15, 10,14, 25);
        Store store2 = storeService.createStore(2,7, 12, 10, 30);
        Store store3 = storeService.createStore(3,15,9,7,45);

        IWorkerService workerService = new WorkerService();
        Worker worker1 = workerService.createWorker("Ivan Ivanov",1,1252.30);
        Worker worker2 = workerService.createWorker("Dimitar Dimitrov",2,1020);
        Worker worker3 = workerService.createWorker("Petar Petrov", 3, 980.90);
        Worker worker4 = workerService.createWorker("Todor Todorov", 4, 1430);
        Worker worker5 = workerService.createWorker("Valeria Dimitrova", 5, 1115.80);
        Worker worker6 = workerService.createWorker("Stefka Stefanova", 6, 999.99);
        Worker worker7 = workerService.createWorker("Aleksandar Aleksandrov", 7, 1350);
        Worker worker8 = workerService.createWorker("Andrea Andreeva", 8, 1000);
        Worker worker9 = workerService.createWorker("Angel Angelov", 9, 1500);
        Worker worker10 = workerService.createWorker("Svetla Georgieva", 10, 990);
        Worker worker11 = workerService.createWorker("Georgi Georgiev", 11, 1200);

        storeService.addWorkers(store1,worker1);
        storeService.addWorkers(store1,worker2);
        storeService.addWorkers(store1,worker3);
        storeService.addWorkers(store1,worker4);

        storeService.addWorkers(store2,worker5);
        storeService.addWorkers(store2,worker6);
        storeService.addWorkers(store2,worker7);
        storeService.addWorkers(store2,worker8);

        storeService.addWorkers(store3,worker9);
        storeService.addWorkers(store3,worker10);
        storeService.addWorkers(store3,worker11);


        IProductService productService = new ProductService();
        Product product1 = productService.createProduct(1, "sugar", 3.2,
                ProductCategory.FOOD, LocalDate.now().plusMonths(24),5.7, 15);
        Product product2 = productService.createProduct(2, "water", 1.5,
                ProductCategory.FOOD, LocalDate.now().plusMonths(32),1, 200);
        Product product3 = productService.createProduct(3, "plastic chair", 5,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(36),15, 30);
        Product product4 = productService.createProduct(4, "toothpaste", 2,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(24),7, 50);
        Product product5 = productService.createProduct(5, "soap bar", 3,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(40),5.9, 120);
        Product product6 = productService.createProduct(6, "strawberries", 5,
                ProductCategory.FOOD, LocalDate.now().plusMonths(1),12, 20);
        Product product7 = productService.createProduct(7, "bananas", 4,
                ProductCategory.FOOD, LocalDate.now().plusMonths(1),8, 30);
        Product product8 = productService.createProduct(8, "flower", 6,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(2),18, 20);
        Product product9 = productService.createProduct(9, "toilet paper: 12 rolls", 3,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(36),12, 40);
        Product product10 = productService.createProduct(10, "cheese", 3,
                ProductCategory.FOOD, LocalDate.now().plusMonths(2),15, 25);
        Product product11 = productService.createProduct(11, "juice", 2,
                ProductCategory.FOOD, LocalDate.now().plusMonths(24),4.8, 70);
        Product product12 = productService.createProduct(12, "chocolate", 2.2,
                ProductCategory.FOOD, LocalDate.now().plusMonths(24),7.6, 200);
        Product product13 = productService.createProduct(13, "napkins", 1.7,
                ProductCategory.NONFOOD, LocalDate.now().plusMonths(36),5,200);
        Product product14 = productService.createProduct(14, "potatoes", 5,
                ProductCategory.FOOD, LocalDate.now().plusMonths(6),3, 50);
        Product product15 = productService.createProduct(15, "ham", 2,
                ProductCategory.FOOD, LocalDate.now().plusMonths(6),8, 250);

        storeService.addProducts(store1, product1);
        storeService.addProducts(store1, product2);
        storeService.addProducts(store1, product3);
        storeService.addProducts(store1, product4);
        storeService.addProducts(store1, product5);

        storeService.addProducts(store2, product6);
        storeService.addProducts(store2, product7);
        storeService.addProducts(store2, product8);
        storeService.addProducts(store2, product9);
        storeService.addProducts(store2, product10);

        storeService.addProducts(store3, product11);
        storeService.addProducts(store3, product12);
        storeService.addProducts(store3, product13);
        storeService.addProducts(store3, product14);
        storeService.addProducts(store3, product15);

        ICashRegisterService cashRegisterService = new CashRegisterService();
        CashRegister cashRegister1 = cashRegisterService.createCashRegister(1);
        CashRegister cashRegister2 = cashRegisterService.createCashRegister(2);
        CashRegister cashRegister3 = cashRegisterService.createCashRegister(3);
        CashRegister cashRegister4 = cashRegisterService.createCashRegister(4);
        CashRegister cashRegister5 = cashRegisterService.createCashRegister(5);
        CashRegister cashRegister6 = cashRegisterService.createCashRegister(6);
        CashRegister cashRegister7 = cashRegisterService.createCashRegister(7);
        CashRegister cashRegister8 = cashRegisterService.createCashRegister(8);
        CashRegister cashRegister9 = cashRegisterService.createCashRegister(9);
        CashRegister cashRegister10 = cashRegisterService.createCashRegister(10);

        storeService.addCashRegisters(store1, cashRegister1);
        storeService.addCashRegisters(store1, cashRegister2);
        storeService.addCashRegisters(store1, cashRegister3);
        storeService.addCashRegisters(store1, cashRegister4);

        storeService.addCashRegisters(store2, cashRegister5);
        storeService.addCashRegisters(store2, cashRegister6);
        storeService.addCashRegisters(store2, cashRegister7);

        storeService.addCashRegisters(store3, cashRegister8);
        storeService.addCashRegisters(store3, cashRegister9);
        storeService.addCashRegisters(store3, cashRegister10);

        CartService cartService = new CartService();

        Cart cart1 = new Cart(1,120);
        CartItem item1 = cartService.createCartItem(store1,1, 4);
        CartItem item2 = cartService.createCartItem(store1, 2, 3);
        cart1.addItems(item1);
        cart1.addItems(item2);

        Cart cart2 = new Cart(2,80);
        CartItem item3 = cartService.createCartItem(store1, 3, 5);
        cart2.addItems(item3);

        Cart cart3 = new Cart(3,50);
        CartItem item4 = cartService.createCartItem(store1, 4, 2);
        CartItem item5 = cartService.createCartItem(store1, 5, 4);
        cart3.addItems(item4);
        cart3.addItems(item5);

        Cart cart4 = new Cart(4,20);
        CartItem item6 = cartService.createCartItem(store1, 1, 3);
        cart4.addItems(item6);

        Cart cart5 = new Cart(5,35);
        CartItem item7 = cartService.createCartItem(store2, 6, 2);
        CartItem item8 = cartService.createCartItem(store2, 8, 1);
        cart5.addItems(item7);
        cart5.addItems(item8);

        Cart cart6 = new Cart(6,72);
        CartItem item9 = cartService.createCartItem(store2, 9, 7);
        cart6.addItems(item9);

        Cart cart7 = new Cart(7,100);
        CartItem item10 = cartService.createCartItem(store2, 7, 4);
        CartItem item11 = cartService.createCartItem(store2, 10, 2);
        CartItem item12 = cartService.createCartItem(store2, 6, 1);
        cart7.addItems(item10);
        cart7.addItems(item11);
        cart7.addItems(item12);

        Cart cart8 = new Cart(8,220);
        CartItem item13 = cartService.createCartItem(store3, 11, 5);
        CartItem item14 = cartService.createCartItem(store3, 14, 2);
        CartItem item15 = cartService.createCartItem(store3, 12, 3);
        cart8.addItems(item13);
        cart8.addItems(item14);
        cart8.addItems(item15);

        Cart cart9 = new Cart(9,25);
        CartItem item16 = cartService.createCartItem(store3, 13, 1);
        cart9.addItems(item16);

        Cart cart10 = new Cart(10,80);
        CartItem item17 = cartService.createCartItem(store3, 15, 8);
        cart10.addItems(item17);

        Cart cart11 = new Cart(11,90);
        CartItem item18 = cartService.createCartItem(store3, 13, 3);
        CartItem item19 = cartService.createCartItem(store3, 11, 2);
        cart11.addItems(item18);
        cart11.addItems(item19);

        Cart cart12 = new Cart(12,45);
        CartItem item20 = cartService.createCartItem(store3, 14, 2);
        cart12.addItems(item20);

        cashRegisterService.addCustomersToQueue(cart1,cashRegister1);
        cashRegisterService.addCustomersToQueue(cart2,cashRegister2);
        cashRegisterService.addCustomersToQueue(cart3,cashRegister2);
        cashRegisterService.addCustomersToQueue(cart4,cashRegister3);

        cashRegisterService.addCustomersToQueue(cart5,cashRegister5);
        cashRegisterService.addCustomersToQueue(cart6,cashRegister5);
        cashRegisterService.addCustomersToQueue(cart7,cashRegister5);

        cashRegisterService.addCustomersToQueue(cart8,cashRegister8);
        cashRegisterService.addCustomersToQueue(cart9,cashRegister8);
        cashRegisterService.addCustomersToQueue(cart10,cashRegister9);
        cashRegisterService.addCustomersToQueue(cart11,cashRegister9);
        cashRegisterService.addCustomersToQueue(cart12,cashRegister10);

        ArrayList<Store> stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        return stores;
    }
}
