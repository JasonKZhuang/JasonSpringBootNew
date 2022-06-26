package com.zkz.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handling with @ControllerAdvice
 * If we want to centralize the exception handling logic to one class which is capable to handle exceptions
 * thrown from any handler class/ controller class – then we can use @ControllerAdvice annotation.
 * By default the methods in an @ControllerAdvice apply globally to all Controllers.
 * We can create a class and add @ControllerAdvice annotation on top.
 * Then add @ExceptionHandler methods for each type of specific exception classes in it.
 * Notice we extended the exception handler class with ResponseEntityExceptionHandler.
 * It is convenient base class for @ControllerAdvice classes that wish to
 * provide centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods.
 *
 * @ControllerAdvice is meta-annotated with @Component ,
 * and therefore can be registered as a Spring bean through component scanning.
 * @RestControllerAdvice is meta-annotated with @ControllerAdvice and @ResponseBody,
 * and that means @ExceptionHandler methods will have their return value rendered via response body message conversion,
 * rather than via HTML views.
 * <p>
 * The @ControllerAdvice annotation has attributes that let you narrow the set of controllers and handlers that they apply to.
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiExceptionMessages> handleAllExceptions(Exception ex, HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiExceptionMessages error = new ApiExceptionMessages("INTERNAL_SERVER_ERROR", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomizedExceptionA.class)
    public final ResponseEntity<ApiExceptionMessages> handleCustomizedExceptionA(CustomizedExceptionA ex, HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiExceptionMessages error = new ApiExceptionMessages("RECORD_NOT_FOUND", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomizedExceptionB.class)
    public final ResponseEntity<ApiExceptionMessages> handleCustomizedExceptionB(CustomizedExceptionB ex, HttpServletRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiExceptionMessages error = new ApiExceptionMessages("RECORD_NOT_FOUND", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
class ExampleAdvice1 {
}

// Target all Controllers within specific packages
@ControllerAdvice("org.example.controllers")
class ExampleAdvice2 {
}

// Target all Controllers assignable to specific classes
// @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
// public class ExampleAdvice3 {}