package com.nminh.bhyt.exception;

public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFoundException(String err){
        super(err);
    }
}
