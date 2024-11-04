package com.redfin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;

    By searchBox = By.id("search-box-input");
    By searchButton = By.cssSelector(".showResults .search-container > .inline-block");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void searchCity(String cityName) {
        WebElement searchBoxElement = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBoxElement.click();
        searchBoxElement.sendKeys(cityName);

        WebElement searchButtonElement = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButtonElement.click();
    }
}
