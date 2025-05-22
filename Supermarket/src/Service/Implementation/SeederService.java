package Service.Implementation;

import Models.*;
import Service.Interface.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeederService implements ISeederService {
    @Override
    public ArrayList<Store> seedStores() {
        IStoreService storeService = new StoreService();
        Store store1 = storeService.createStore(15, 10,14, 25);
        Store store2 = storeService.createStore(7, 12, 10, 30);
        Store store3 = storeService.createStore(15,9,7,45);

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

        storeService.addWorkers(store1,worker2);
        storeService.addWorkers(store1,worker7);
        storeService.addWorkers(store1,worker11);
        storeService.addWorkers(store1,worker4);

        storeService.addWorkers(store2,worker3);
        storeService.addWorkers(store2,worker10);
        storeService.addWorkers(store2,worker5);
        storeService.addWorkers(store2,worker8);

        storeService.addWorkers(store3,worker1);
        storeService.addWorkers(store3,worker6);
        storeService.addWorkers(store3,worker9);


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

       // storeService.addProducts();

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

        Cart cart1 = new Cart(1,120);
        Cart cart2 = new Cart(2,80);
        Cart cart3 = new Cart(3,50);
        Cart cart4 = new Cart(4,20);
        Cart cart5 = new Cart(5,35);
        Cart cart6 = new Cart(6,72);
        Cart cart7 = new Cart(7,100);
        Cart cart8 = new Cart(8,220);
        Cart cart9 = new Cart(9,25);
        Cart cart10 = new Cart(10,80);
        Cart cart11 = new Cart(11,90);
        Cart cart12 = new Cart(12,45);
        Cart cart13 = new Cart(13,32);
        Cart cart14 = new Cart(14,66);
        Cart cart15 = new Cart(15,88);
        Cart cart16 = new Cart(16,130);
        Cart cart17 = new Cart(17,27);
        Cart cart18 = new Cart(18,10);
        Cart cart19 = new Cart(19,5);
        Cart cart20 = new Cart(20,31);





    }
}
