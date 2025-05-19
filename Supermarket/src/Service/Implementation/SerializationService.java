package Service.Implementation;

import Models.Receipt;
import Service.Interface.ISerializationService;

import java.io.*;

public class SerializationService implements ISerializationService {
    @Override
    public void serialization(Receipt receipt) throws FileNotFoundException {
        String fileName = "./receipts/"+receipt.getReceiptId()+".ser";
        File file = new File(fileName);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()){
            parent.mkdirs();
        }
        try(FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
            ostream.writeObject(receipt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Receipt deserialization(int receiptId) {
      File file = new File("./receipts/"+receiptId+".ser");
      Receipt receipt = null;
      if(file.exists()){
          try(FileInputStream stream = new FileInputStream(file);
              ObjectInputStream istream = new ObjectInputStream(stream)) {
             receipt = (Receipt)istream.readObject();
          } catch (IOException e) {
              throw new RuntimeException(e);
          } catch (ClassNotFoundException e) {
              throw new RuntimeException(e);
          }
      }
      return receipt;
    }
}
