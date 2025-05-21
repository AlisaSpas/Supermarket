package Service.Implementation;

import Models.*;
import Service.Interface.ICashRegisterService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Queue;

public class CashRegisterService implements ICashRegisterService {
    @Override
    public CashRegister createCashRegister(int number) {
        CashRegister cashRegister = new CashRegister(number);
        return cashRegister;
    }

    @Override
    public ArrayList<Receipt> checkoutCustomers(LocalDateTime timeOfSale,
                                    Queue<Cart> customers, Store store, CashRegister cashRegister) {
        int workerId = cashRegister.getWorkerId();
        //TODO
        if(workerId == 0){
            return null;
        }

        ArrayList<Receipt> receipts = new ArrayList<>();
        //TODO
        int receiptId = 1;
        while(!customers.isEmpty()){

            ArrayList<CartItem> items = customers.poll().getItems();
            ArrayList<SoldProduct> soldProducts = new ArrayList<>();
            double total = 0;
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                total += item.getProductPrice()* item.getQuantity();
                Product product = getProduct(store, item.getProductId());
                //TODO
                if(product == null){
                    continue;
                }
                SoldProduct soldProduct = new SoldProduct(product.getProductName(), item.getProductId(),
                        item.getProductPrice(),item.getQuantity());
                soldProducts.add(soldProduct);
            }

            Receipt receipt = new Receipt(receiptId,workerId,timeOfSale,total, soldProducts);
            receipts.add(receipt);
            receiptId++;
        }
        return receipts;
    }

    @Override
    public void addCustomersToQueue(Cart cart, CashRegister cashRegister) {
        cashRegister.addCustomers(cart);
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
