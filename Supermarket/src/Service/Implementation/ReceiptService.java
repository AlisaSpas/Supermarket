package Service.Implementation;

import Models.Receipt;
import Models.SoldProduct;
import Service.Interface.IReceiptService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceiptService implements IReceiptService {
    @Override
    public Receipt createReceipt(int receiptId, int workerId, LocalDateTime timeOfSale,
                                 double total, ArrayList<SoldProduct> soldProducts) {
        Receipt receipt = new Receipt(receiptId,workerId,timeOfSale,total,soldProducts);
        return receipt;
    }

    @Override
    public void printReceipt(Receipt receipt) {
        System.out.println("Receipt id: "+receipt.getReceiptId());
        System.out.println("Worker id: "+receipt.getWorkerId());
        ArrayList<SoldProduct> soldProducts = receipt.getSoldProducts();
        for (int i = 0; i < soldProducts.size(); i++) {
            SoldProduct product = soldProducts.get(i);
            //TODO
            if(product == null){
                continue;
            }
            System.out.println("Product name: "+product.getProductName()+" ,quantity: "+
                    product.getQuantity()+" ,price: "+product.getProductPrice());
        }
        System.out.println(receipt.getTotal());
        System.out.println(receipt.getTimeOfSale());
    }
}
