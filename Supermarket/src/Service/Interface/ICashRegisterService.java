package Service.Interface;

import Models.Cart;
import Models.CashRegister;
import Models.Receipt;
import Models.Store;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Queue;

public interface ICashRegisterService {
    public CashRegister createCashRegister(int number);
    public ArrayList<Receipt> checkoutCustomers(LocalDateTime timeOfSale, Queue<Cart> customers,
                                              Store store, CashRegister cashRegister);
    public void addCustomersToQueue(Cart cart, CashRegister cashRegister);
}
