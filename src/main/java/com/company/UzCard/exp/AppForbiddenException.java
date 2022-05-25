package com.company.UzCard.exp;

public class AppForbiddenException extends RuntimeException{
    public AppForbiddenException(String message) {
        super(message);
    }
}
