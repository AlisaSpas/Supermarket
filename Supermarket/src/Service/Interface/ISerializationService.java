package Service.Interface;

import Models.Receipt;

import java.io.FileNotFoundException;

public interface ISerializationService {

    public void serialization(Receipt receipt) throws FileNotFoundException;
    public Receipt deserialization(Receipt receipt);
}
