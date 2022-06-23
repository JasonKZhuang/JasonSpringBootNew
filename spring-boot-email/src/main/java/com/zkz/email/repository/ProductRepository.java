package com.zkz.email.repository;

import com.zkz.email.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jason Zhuang on 17/11/21.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}