package com.zkz.exception.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomizedExceptionA extends RuntimeException {
    public CustomizedExceptionA(String exception) {
        super(exception);
    }
}