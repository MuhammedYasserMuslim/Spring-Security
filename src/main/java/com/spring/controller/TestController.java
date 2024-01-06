package com.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class TestController {


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "ADMIN";
    }
    @PreAuthorize("hasRole('MANGER') or hasRole('ADMIN')")
    @GetMapping("/manger")
    public String manager() {
        return "MANGER";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "USER";
    }


}
