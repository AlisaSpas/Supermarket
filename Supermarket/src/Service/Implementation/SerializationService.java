package Service.Implementation;

import Models.Receipt;
import Service.Interface.ISerializationService;

import java.io.*;

public class SerializationService implements ISerializationService {
    @Override
    public void serialization(Receipt receipt) throws FileNotFoundException {
        String fileName = "./receipts/"+receipt.getStoreId()+"_"+receipt.getReceiptId()+".ser";
        File file = new File(fileName);
        File parent = file.getParentFile();
        if(parent != null && !parent.exists()){
            parent.mkdirs();
        }
        try(FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(stream)) {
            ostream.writeObject(receipt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Receipt deserialization(String name) {
      File file = new File("./receipts/"+name+".ser");
      Receipt receipt = null;
      if(file.exists()){
          try(FileInputStream stream = new FileInputStream(file);
              ObjectInputStream istream = new ObjectInputStream(stream)) {
             receipt = (Receipt)istream.readObject();
          } catch (IOException | ClassNotFoundException e) {
              System.out.println(e.getMessage());
          }
      }
      return receipt;
    }
}
