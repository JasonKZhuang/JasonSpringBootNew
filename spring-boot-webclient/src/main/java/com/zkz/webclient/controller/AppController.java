package com.zkz.webclient.controller;

import com.zkz.webclient.dto.ProductDTO;
import com.zkz.webclient.dto.ResponseDTO;
import com.zkz.webclient.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason Zhuang 19/17/22
 */

//RestController indicates that the data returned by each method will be written straight into the response body
//instead of rendering a template.
@Slf4j
@RestController
@RequestMapping("/api/v1/endpoint")
@RequiredArgsConstructor
public class AppController {
    //inject AppService bean into the controller.
    private final AppService appService;

    @GetMapping
    public String welcome() {
        return "Hello Microservices";
    }
    @GetMapping("/users")
    public List<Object> send() {
        return appService.callGetByWebClient();
    }
    @PostMapping("/send")
    public Map<String,String> send(@RequestBody ProductDTO productDTO) {
        Map<String,String> map = appService.callPostByWebClient();
        return map;
    }
}
