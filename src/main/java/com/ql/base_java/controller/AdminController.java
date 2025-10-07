package com.ql.base_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        return "Admin test endpoint is working!";
    }

}
