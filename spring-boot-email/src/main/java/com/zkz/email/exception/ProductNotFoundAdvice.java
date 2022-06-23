package com.zkz.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jason Zhuang 17/11/2021
 */
@ControllerAdvice
public class ProductNotFoundAdvice {

    /**
     * @ResponseBody signals that this advice is rendered straight into the response body.
     * @ExceptionHandler configures the advice to only respond if an ProductNotFoundException is thrown.
     * @ResponseStatus says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
     * The body of the advice generates the content. In this case, it gives the message of the exception.
     */
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }

}