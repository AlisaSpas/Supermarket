package Service.Interface;

import Models.Receipt;
import Models.SoldProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IReceiptService {
    public Receipt createReceipt(int receiptId, int workerId, LocalDateTime timeOfSale,
                                 double total, ArrayList<SoldProduct> soldProducts, int storeId);
    public void printReceipt(Receipt receipt);
}
