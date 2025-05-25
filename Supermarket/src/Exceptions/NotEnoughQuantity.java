package Exceptions;

public class NotEnoughQuantity extends Exception{
    public NotEnoughQuantity(int productId, String productName, double availableQuantity) { super("Not enough quantity for product: " + productName +"(Id: " + productId + "). Available quantity: " + availableQuantity); }

}
