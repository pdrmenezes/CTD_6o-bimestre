package com.Tests;

import com.Pages.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageTest {
  private static SearchPage page;

  @BeforeEach
  public void setUp() {
    page = new SearchPage();
    page.openApp();
  }

  @Test
  @Tag("Search Product & Add to Cart")
  public void successfullyAddProductToCart() throws InterruptedException {
    page.searchByText("Iphone");
    Thread.sleep(500);
    page.addProductToCart();
    Thread.sleep(500);

    String successMessage = page.getSuccessMessage();
    assertTrue(successMessage.contains("Success: You have added iPhone to your shopping cart!"));
    System.out.println("Product successfully added to cart!");
  }

  @AfterEach
  public void closeBrowser() {
    page.quitDriver();
    System.out.println("The Driver has been closed.");
  }
}
