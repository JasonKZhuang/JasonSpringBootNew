package com.zkz.exception.controller;

import com.zkz.exception.exception.CustomizedExceptionA;
import com.zkz.exception.exception.CustomizedExceptionB;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 * 1. Spring @ExceptionHandler annotation
 * To handle exceptions in String MVC, we can define a method in controller class and use the annotation @ExceptionHandler on it.
 * Spring configuration will detect this annotation and register the method as exception handler for argument exception class and its subclasses.
 * 2. Spring @ExceptionHandler – Handle multiple exceptions
 * As mentioned earlier, above exception handler will handle all exceptions which are either instance of given class or sub-classes of argument exception.
 * But, if we want to configure @ExceptionHandler for multiple exceptions of different types,
 * then we can specify all such exceptions in form of array.
 *
 * Reference:
 * 1. https://howtodoinjava.com/spring-core/spring-exceptionhandler-annotation/
 */

@RestController
@RequestMapping("/api/v1")
public class AppController {


    @RequestMapping(value = "/single", method = RequestMethod.GET, headers = "Accept=*/*")
    public @ResponseBody
    ModelAndView oneFaultyMethod() {
        if (true) {
            throw new NullPointerException("This error message if for demo only.");
        }
        return null;
    }

    @RequestMapping(value = "/multiple", method = RequestMethod.GET, headers = "Accept=*/*")
    public @ResponseBody
    ModelAndView multipleFaultyMethod() {
        if (true) {
            throw new IndexOutOfBoundsException("Out of index of bounds");
        }
        return null;
    }

    /**
     * this method will go to the global exception handler
     *
     * @return
     */
    @RequestMapping(value = "/globalA", method = RequestMethod.GET, headers = "Accept=*/*")
    public String handleGlobalExceptionA() {
        if (true) {
            throw new CustomizedExceptionA("This is testing exception message for AAAAAA");
        }
        return null;
    }

    /**
     * this method will go to the global exception handler
     *
     * @return
     */
    @RequestMapping(value = "/globalB", method = RequestMethod.GET, headers = "Accept=*/*")
    public String handleGlobalExceptionB() {
        if (true) {
            throw new CustomizedExceptionB("This is testing exception message for BBBBBB");
        }
        return null;
    }

    /**
     * this method only handles NullPointerException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleSingleException(NullPointerException ex) {
        //Do something additional if required
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    /**
     * this method handles whatever exception in the annotation array
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({IllegalAccessException.class, IndexOutOfBoundsException.class})
    public ModelAndView handleMultipleException(Exception ex) {
        //Do something additional if required
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({SQLException.class, SQLDataException.class})
    public String handleDatabaseError() {
        // Nothing to do.  Returns the logical view name of an error page, passed
        // to the view-resolver(s) in usual way.
        // Note that the exception is NOT available to this view (it is not added
        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
        // below.
        return "databaseError";
    }
}
