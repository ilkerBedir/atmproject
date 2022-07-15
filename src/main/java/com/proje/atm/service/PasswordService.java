package com.proje.atm.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {
  private BCryptPasswordEncoder passwordEncoder;

  public PasswordService() {
    passwordEncoder = new BCryptPasswordEncoder();
  }

  public String passwordEncrypt(String password) {
    String encode = passwordEncoder.encode(password);
    return encode;
  }

  public boolean verifyPassword(String encodedPassword, String requestPassword) {
    return passwordEncoder.matches(requestPassword, encodedPassword);
  }
}
