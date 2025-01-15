package com.estafanini.pruebatecnica.LRCRR.Exeptions;

public class EmailAlreadyExistException extends Exception {

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
