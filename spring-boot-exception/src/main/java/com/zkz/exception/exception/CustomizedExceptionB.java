package com.zkz.exception.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomizedExceptionB extends RuntimeException {
    public CustomizedExceptionB(String exception) {
        super(exception);
    }
}