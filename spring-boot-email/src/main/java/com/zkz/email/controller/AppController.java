package com.zkz.email.controller;

import com.zkz.email.dto.EmailObject;
import com.zkz.email.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String welcome() {
        return "Hello Microservices";
    }

    @PostMapping("send")
    public String send(@RequestBody EmailObject emailObject) {
        appService.sendSimpleMessage(emailObject);
        return "ok";
    }

    @PostMapping("send2")
    public String sendWithAttachment(@RequestBody EmailObject emailObject) {
        appService.sendMessageWithAttachment(emailObject);
        return "ok";
    }
}
