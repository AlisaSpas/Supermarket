package Service.Interface;

import Exceptions.NoWorkerException;
import Exceptions.NotEnoughQuantity;
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
                                              Store store, CashRegister cashRegister) throws NoWorkerException;
    public void addCustomersToQueue(Cart cart, CashRegister cashRegister);
    public void setWorkerToCashRegister(int workerId, int cashRegisterNumber, Store store);
    public void removeWorkerFromCashRegister(int workerId, int cashRegisterNumber, Store store);
}
