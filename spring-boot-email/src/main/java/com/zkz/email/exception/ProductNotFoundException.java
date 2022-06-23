package com.zkz.email.exception;

/**
 * Created by Jason Zhuang on 17/11/21.
 * description:
 * it is used to indicate when an product is looked up but not found.
 */
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Could not find product " + id);
  }

}