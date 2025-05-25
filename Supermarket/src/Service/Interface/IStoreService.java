package Service.Interface;

import Models.*;

public interface IStoreService {
    public Store createStore(int id,double overpriceFood, double overpriceNonfood,
                             int daysTillExpiration, double discount);

    public void addProducts(Store store, Product product);

    public void addWorkers(Store store, Worker worker);

    public void addCashRegisters(Store store, CashRegister cashRegister);

    public void printProducts(Store store);
    public void printWorkers(Store store);
    public void printCart(Store store, Cart cart);
    public void printCashRegisters(Store store);

    public String getReport(Store store);
}
