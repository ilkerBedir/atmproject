package com.proje.atm.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PasswordServiceTest {

  @Autowired
  PasswordService passwordService;

  @Test
  @DisplayName("Verify Password Test")
  public void verifyPassword() {

    String str = "123456";
    String str1 = "1123456";
    String encryptedPassword = passwordService.passwordEncrypt(str);
    assertTrue(passwordService.verifyPassword(encryptedPassword, str));
    assertFalse(passwordService.verifyPassword(encryptedPassword, str1));

  }
}