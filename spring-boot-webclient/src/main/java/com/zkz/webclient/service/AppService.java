package com.zkz.webclient.service;

import com.zkz.webclient.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jason Zhuang on 17/11/21.
 */
@Slf4j
@Service
public class AppService {

    //inject RestTemplate, so we can call another api endpoints which are outside this service
    @Autowired
    private RestTemplate restTemplate;

    // using RestTemplate to call the other endpoint which is outside this service,
    // communicating with other microservice
    public ProductDTO callOutsideEndpoint(String userId){
        String myUrl = "https://localhost:8093/users" + userId;
        ProductDTO obj = restTemplate.getForObject(myUrl, ProductDTO.class);
        return obj;
    }
}
