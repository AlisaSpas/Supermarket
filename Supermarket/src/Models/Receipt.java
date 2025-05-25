package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receipt implements Serializable {
    int receiptId;
    int workerId;
    LocalDateTime timeOfSale;
    ArrayList<SoldProduct> soldProducts;
    double total;
    int storeId;

    public Receipt(int receiptId, int workerId, LocalDateTime timeOfSale, double total, ArrayList<SoldProduct> products, int storeId){
        this.receiptId = receiptId;
        this.workerId = workerId;
        this.timeOfSale = timeOfSale;
        this.total = total;
        this.soldProducts = products;
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
    public int getReceiptId() {
        return receiptId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
    public int getWorkerId() {
        return workerId;
    }

    public void setTimeOfSale(LocalDateTime timeOfSale) {
        this.timeOfSale = timeOfSale;
    }
    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }

    public void setSoldProducts(ArrayList<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }
    public ArrayList<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public double getTotal() {
        return total;
    }
}
