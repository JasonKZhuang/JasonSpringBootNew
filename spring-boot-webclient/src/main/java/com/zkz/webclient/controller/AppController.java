package com.zkz.webclient.controller;

import com.zkz.webclient.dto.ProductDTO;
import com.zkz.webclient.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jason Zhuang 17/07/2022
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

    @PostMapping("send")
    public String send(@RequestBody ProductDTO productDTO) {
//        appService.sendSimpleMessage(emailObject);
        return "ok";
    }
}
