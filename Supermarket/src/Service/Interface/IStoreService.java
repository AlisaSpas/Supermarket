package Service.Interface;

import Models.Product;
import Models.Store;
import Models.Worker;

public interface IStoreService {
    public Store createStore(double overpriceFood, double overpriceNonfood,
                             int daysTillExpiration, double discount);

    public void addProducts(Store store, Product product);

    public void addWorkers(Store store, Worker worker);

    public void printProducts(Store store);
    public void printWorkers(Store store);
}
