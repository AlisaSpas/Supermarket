package Service.Implementation;

import Models.Product;
import Models.Store;
import Models.Worker;
import Service.Interface.IStoreService;

import java.util.ArrayList;

public class StoreService implements IStoreService {

    @Override
    public Store createStore(double overpriceFood, double overpriceNonfood, int daysTillExpiration, double discount) {
        Store store = new Store(overpriceFood,overpriceNonfood,daysTillExpiration,discount);
        return store;
    }

    @Override
    public void addProducts(Store store, Product product) {
        store.addProducts(product);
    }

    @Override
    public void addWorkers(Store store, Worker worker) {
        store.addWorkers(worker);
    }

    @Override
    public void printProducts(Store store) {
        ArrayList<Product> products = store.getProducts();
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            System.out.println("Product name: "+product.getProductName()+" , price: "+product.getProductPrice());
        }
    }

    @Override
    public void printWorkers(Store store) {
        ArrayList<Worker> workers = store.getWorkers();
        for (int i = 0; i < workers.size(); i++) {
            Worker worker = workers.get(i);
            System.out.println("Worker name: "+worker.getWorkerName()+" with salary: "+worker.getMonthlySalary());
        }

    }
}
