package com.nminh.bhyt.exception;

public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BadRequestException(String err){
        super(err);
    }
}
