package Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Store {
    int id;
    Set<Worker> workers;
    double overpriceFood;
    double overpriceNonfood;
    int daysTillExpiration;
    double discount;
    ArrayList<Product> products;
    ArrayList<CashRegister> cashRegisters;

    public Store (int id,double overpriceFood, double overpriceNonfood,int daysTillExpiration, double discount){
        this.overpriceFood = overpriceFood;
        this.overpriceNonfood = overpriceNonfood;
        this.daysTillExpiration = daysTillExpiration;
        this.discount = discount;
        this.id = id;

        this.workers = new HashSet<>();
        this.products = new ArrayList<>();
        this.cashRegisters = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addWorkers(Worker worker) {
        this.workers.add(worker);
    }
    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setOverpriceFood(double overpriceFood) {
        this.overpriceFood = overpriceFood;
    }
    public double getOverpriceFood() {
        return overpriceFood;
    }

    public void setOverpriceNonfood(double overpriceNonfood) {
        this.overpriceNonfood = overpriceNonfood;
    }
    public double getOverpriceNonfood() {
        return overpriceNonfood;
    }

    public void setDaysTillExpiration(int daysTillExpiration) {
        this.daysTillExpiration = daysTillExpiration;
    }
    public int getDaysTillExpiration() {
        return daysTillExpiration;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getDiscount() {
        return discount;
    }

    public void addProducts(Product product) {
        this.products.add(product);
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addCashRegisters(CashRegister cashRegister) {
        this.cashRegisters.add(cashRegister);
    }
    public ArrayList<CashRegister> getCashRegisters(){
        return cashRegisters;
    }
}
