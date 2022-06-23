package com.zkz.email.service;

import com.zkz.email.entity.Product;
import com.zkz.email.entity.User;
import com.zkz.email.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Jason Zhuang on 17/11/21.
 */
@Slf4j
@Service
public class AppService {

    //inject RestTemplate, so we can call another api endpoints which are outside this service
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository repProduct;

    public List<Product> getProducts() {
        return repProduct.findAll();
    }

    public Product getProductById(long id) {
        Product retValue = new Product();
        retValue = repProduct.getById(id);
        return retValue;
    }

    // using RestTemplate to call the other endpoint which is outside this service,
    // communicating with other microservice
//    public User callOutsideEndpoint(String userId) {
//        String myUrl = "https://localhost:8093/users" + userId;
//        User u = restTemplate.getForObject(myUrl, User.class);
//        return u;
//    }
}
