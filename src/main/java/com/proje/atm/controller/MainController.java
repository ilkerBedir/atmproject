package com.proje.atm.controller;

import com.proje.atm.model.CustomerResponseModel;
import com.proje.atm.model.LoginRequestModel;
import com.proje.atm.model.RegisterRequestModel;
import com.proje.atm.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

  @Autowired
  MainService mainService;

  @PostMapping("/login")
  public CustomerResponseModel login(@RequestBody LoginRequestModel requestModel) {
    return mainService.login(requestModel);
  }

  @PostMapping("/register")
  public HttpStatus register(@RequestBody RegisterRequestModel requestModel) {
    mainService.register(requestModel);
    return HttpStatus.OK;
  }
}
