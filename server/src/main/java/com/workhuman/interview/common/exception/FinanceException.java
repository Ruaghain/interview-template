package com.workhuman.interview.common.exception;

public class FinanceException extends RuntimeException {

    public FinanceException(String errorMessage) {
        super(errorMessage);
    }

    public FinanceException(Exception e) {
        super(e);
    }
}
