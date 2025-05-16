import Models.Product;
import Models.ProductCategory;
import Models.Store;
import Models.Worker;
import Service.Implementation.ProductService;
import Service.Implementation.StoreService;
import Service.Implementation.WorkerService;
import Service.Interface.IProductService;
import Service.Interface.IStoreService;
import Service.Interface.IWorkerService;

import java.time.LocalDate;

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

        store.addWorkers(worker1);
        store.addWorkers(worker2);
        store.addProducts(product1);
        store.addProducts(product2);

        storeService.printProducts(store);
        storeService.printWorkers(store);
    }
}