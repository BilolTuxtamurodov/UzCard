package com.company.UzCard.exp;

public class ItemAlreadyExistsException extends RuntimeException{
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
