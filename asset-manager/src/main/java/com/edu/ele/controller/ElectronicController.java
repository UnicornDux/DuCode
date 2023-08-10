package com.edu.ele.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.api.dubbo.UserService;

@RestController
@RequestMapping("/electronic")
public class ElectronicController {

  @DubboReference
  private UserService userService;

  @RequestMapping("/get")
  public String get() {
    return userService.getUserInfo("12138");
  }

  @PostMapping("/local")
  public String getLocalConst() {
    return null;
  }
}
