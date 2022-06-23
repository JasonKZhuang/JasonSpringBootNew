package com.zkz.email.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkz.email.dto.ResponseDTO;
import com.zkz.email.entity.Product;
import com.zkz.email.exception.ProductNotFoundException;
import com.zkz.email.repository.ProductRepository;
import com.zkz.email.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jason Zhuang 17/11/2021
 */
@Slf4j
//RestController indicates that the data returned by each method will be written straight into the response body
//instead of rendering a template.
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class AppController {
    //inject AppService bean into the controller.
    @Autowired
    private final AppService appService;

    //inject ProductRepository interface bean into the controller.
    @Autowired
    private final ProductRepository repProduct;

    //========== endpints ========================================//
    @GetMapping("/products")
    public List<Product> getAll() {
        return repProduct.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getOne(@PathVariable Long id) {
        return repProduct.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/product")
    public Product newProduct(@RequestBody Product newProduct) {
        return repProduct.save(newProduct);
    }

    @PutMapping("/products/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return repProduct.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    return repProduct.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repProduct.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repProduct.deleteById(id);
    }

    //applying RequestParam on the endpoint,it would be /one?id=1
    //using RequestMapping refer to this:
    //https://drive.google.com/file/d/1ON23O-XRDdTFYAkvAHKWH8hhgIDQsDUn/view
    @RequestMapping(method = RequestMethod.GET, value = "/one")
    public ResponseEntity<ResponseDTO> myGetMethod(@RequestParam("id") String productId) {
        log.info("Come into method: {}", "myGetMethod");
        long pId = Long.valueOf(productId).longValue();
        Product p;
        ObjectMapper mapper = new ObjectMapper();
        try {
            p = appService.getProductById(pId);
            System.out.println(p.toString());
        } catch (Exception e) {
            log.error("something wrong");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success!");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
