package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {
  protected static final String searchInputXpath = "//*[@id=\"search\"]/input";
  protected static final String searchBtnXpath = "//*[@id=\"search\"]/span/button";
  protected static final String addToCartBtnXpath = "//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]";
  protected static final String successMessageXpath = "//*[@id=\"product-search\"]/div[1]";

  public void searchByText(String text) {
    WebElement searchInput = getWebElement(By.xpath(searchInputXpath));
    searchInput.clear();
    searchInput.sendKeys(text);
    WebElement searchBtn = getWebElement(By.xpath(searchBtnXpath));
    searchBtn.click();
  }

  public void addProductToCart() {
    WebElement addToCartBtn = getWebElement(By.xpath(addToCartBtnXpath));
    addToCartBtn.click();
  }

  public String getSuccessMessage() {
    WebElement successMessage = getWebElement(By.xpath(successMessageXpath));
    return successMessage.getText();
  }
}
