package com.company.UzCard.exp;

public class NotenoughMoneyException extends RuntimeException{
    public NotenoughMoneyException(String message) {
        super(message);
    }
}
