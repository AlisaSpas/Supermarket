package Exceptions;

public class NoProductExeption extends Exception {
    public NoProductExeption(int id) {
        super("Product Id: " + id + " does not exist!");
    }
}
