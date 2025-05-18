package Service.Interface;

import Models.Cart;
import Models.CashRegister;
import Models.Receipt;
import Models.Store;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ICashRegisterService {
    public CashRegister createCashRegister(int number);
    public Receipt checkoutCustomer(int receiptId, LocalDateTime timeOfSale, Cart cart, Store store, CashRegister cashRegister);
}
