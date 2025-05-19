package Service.Implementation;

import Models.Receipt;
import Models.SoldProduct;
import Service.Interface.ITxtService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TxtService implements ITxtService {
    @Override
    public void writeTxtFile(Receipt receipt) throws IOException {
        String fileName = "./receiptsTxt/"+receipt.getReceiptId()+".txt";
        File file = new File(fileName);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()){
            parent.mkdirs();
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            StringBuilder content = new StringBuilder("Receipt id: " + receipt.getReceiptId() + "\n" + "Worker id: " +
                    receipt.getWorkerId() + "\n");
            ArrayList<SoldProduct> soldProducts = receipt.getSoldProducts();
            for (int i = 0; i < soldProducts.size(); i++) {
                SoldProduct product = soldProducts.get(i);
                content.append("Product name: ").append(product.getProductName()).append(" ,quantity: ").
                        append(product.getQuantity()).append(" ,price: ").append(product.getProductPrice());

            }
            content.append("\n");
            content.append("Total: ").append((receipt.getTotal())).append("\n");
            content.append("Time of sale: ").append(receipt.getTimeOfSale());
            writer.write(content.toString());
        }
    }
}
