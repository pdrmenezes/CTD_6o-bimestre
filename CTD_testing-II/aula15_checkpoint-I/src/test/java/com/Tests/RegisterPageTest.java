package com.Tests;

import com.Pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPageTest {
  private static RegisterPage page;

  @BeforeEach
  public void setUp() {
    page = new RegisterPage();
    page.openApp();
  }

  @Test
  @Tag("Register from Homepage")
  public void successfulRegisterFlowTest() throws InterruptedException {
    page.goRegisterPage();
    page.inputRegisterData("Ann", "Cortez", "duis.dignissim@yahoo.couk", "1-337-951-1613", "EEK42BTE5BJ");
    Thread.sleep(1000);
    page.pressContinueButton();
    Thread.sleep(1000);
    String message = page.getSuccessMessage();

    assertTrue(message.contains("Congratulations! Your new account has been successfully created!"));
    System.out.println("User Successfully Created!");
  }

  @AfterEach
  public void closeBrowser() {
    page.quitDriver();
    System.out.println("The Driver has been closed.");
  }

}
