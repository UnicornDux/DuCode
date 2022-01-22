package com.edu.ele.controller;


import com.edu.api.dubbo.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/electronic")
@RestController
public class ElectronicController {


    @DubboReference
    private UserService userService;


    @RequestMapping("/get")
    public String get(){
        return userService.getUserInfo("12138");
    }

}
