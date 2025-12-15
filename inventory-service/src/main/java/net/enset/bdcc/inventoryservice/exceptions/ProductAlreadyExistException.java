package net.enset.bdcc.inventoryservice.exceptions;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
