package com.sdw.securitydemo1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author: 索德文
 * @date 2021/5/6 19:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_sale", "ROLE_manager"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update() {
        return "hello update";
    }
}
