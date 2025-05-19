package Service.Interface;

import Models.Receipt;

import java.io.IOException;

public interface ITxtService {
    public void writeTxtFile (Receipt receipt) throws IOException;
}
