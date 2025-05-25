package Service.Implementation;

import Models.*;
import Service.Interface.IStoreService;

import java.util.ArrayList;
import java.util.Set;

public class StoreService implements IStoreService {

    @Override
    public Store createStore(int id,double overpriceFood, double overpriceNonfood, int daysTillExpiration, double discount) {
        Store store = new Store(id,overpriceFood,overpriceNonfood,daysTillExpiration,discount);
        return store;
    }

    @Override
    public void addProducts(Store store, Product product) {
        double overprice;
        if(product.getCategory() == ProductCategory.FOOD){
            overprice = store.getOverpriceFood();
        }else{
            overprice = store.getOverpriceNonfood();
        }
        double price = product.getProductPrice();
        price += (price * overprice)/100;
        product.setProductPrice(price);
        store.addProducts(product);
    }

    @Override
    public void addWorkers(Store store, Worker worker) {
        store.addWorkers(worker);
    }

    @Override
    public void addCashRegisters(Store store, CashRegister cashRegister) {
        store.addCashRegisters(cashRegister);
    }

    @Override
    public void printProducts(Store store) {
        ArrayList<Product> products = store.getProducts();
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            System.out.println("Product name: "+product.getProductName()+", product id: "+ product.getProductId()+
                    " , price: "+product.getProductPrice() + ", quantity: " + product.getQuantity());
        }
    }

    @Override
    public void printWorkers(Store store) {
        Set<Worker> workers = store.getWorkers();
        workers.forEach(worker -> System.out.println("Id: "+ worker.getWorkerId() + " Worker name: "+worker.getWorkerName()+" with salary: "+
              worker.getMonthlySalary()));
    }

    @Override
    public void printCart(Store store, Cart cart) {
        ArrayList<CartItem> items = cart.getItems();
        for(int i = 0; i < items.size(); i++){
            CartItem item = items.get(i);
            Product product = getProduct(store, item.getProductId());
            if(product != null){
                System.out.println("Product name: "+product.getProductName()+
                        " product price: "+product.getProductPrice());
            }

        }
    }

    @Override
    public void printCashRegisters(Store store) {
        ArrayList<CashRegister> registers = store.getCashRegisters();
        for(int i = 0; i < registers.size(); i++){
            CashRegister register = registers.get(i);
            if(register != null){
               System.out.println("Cash register number: "+ register.getNumber());
            }
        }
    }

    private Product getProduct(Store store,int idToBeFound){
        ArrayList<Product> products = store.getProducts();
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            if(product.getProductId() == idToBeFound){
                return product;
            }
        }
        return null;
    }
}
