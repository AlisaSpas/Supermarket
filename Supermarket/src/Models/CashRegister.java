package Models;

import java.util.LinkedList;
import java.util.Queue;

public class CashRegister {
    int workerId;
    int number;
    Queue<Cart> customers;

    public CashRegister(int number){
        this.number = number;
        this.customers = new LinkedList<>();
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
    public int getWorkerId() {
        return workerId;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void addCustomers(Cart cart) {
        this.customers.add(cart);
    }
    public Queue<Cart> getCustomers() {
        return customers;
    }
    public void pollCustomer(){
        this.customers.poll();
    }
}
