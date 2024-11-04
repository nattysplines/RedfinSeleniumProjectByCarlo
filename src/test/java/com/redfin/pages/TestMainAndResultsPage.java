package com.redfin.pages;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestMainAndResultsPage {

    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    ResultsPage resultsPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver, wait);
        resultsPage = new ResultsPage(driver, wait);
        driver.get("https://www.redfin.com/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testMainAndResultsPage() throws InterruptedException {
        int maxPrice = 1000000;
        int bedrooms = 2;
        int bathrooms = 2;

        mainPage.searchCity("Portland");

        resultsPage.openFilters();
        resultsPage.setMaxPrice(maxPrice);
        resultsPage.setBedrooms(bedrooms);
        resultsPage.setBathrooms(bathrooms);
        resultsPage.applyFilters();

        verifyListings(maxPrice, bedrooms, bathrooms);
    }

    private void verifyListings(int maxPrice, int minBeds, double minBaths) throws InterruptedException {
        resultsPage.getHomeCards();

        // Short sleep to let React components fully update
        Thread.sleep(500);

        // Reconfirm visibility after delay
        List<WebElement> homeCards = resultsPage.getHomeCards();

        for (WebElement homeCard : homeCards) {
            WebElement priceElement = homeCard.findElement(resultsPage.priceLocator);
            String priceText = priceElement.getText().replaceAll("[^\\d]", "");
            int price = Integer.parseInt(priceText);
            Assert.assertTrue(price < maxPrice);

            WebElement bedsElement = homeCard.findElement(resultsPage.bedsLocator);
            String bedsText = bedsElement.getText().split(" ")[0];
            int beds = Integer.parseInt(bedsText);
            Assert.assertTrue(beds >= minBeds);

            WebElement bathsElement = homeCard.findElement(resultsPage.bathsLocator);
            String bathsText = bathsElement.getText().split(" ")[0];
            double baths = Double.parseDouble(bathsText);
            Assert.assertTrue(baths >= minBaths);
        }
    }
}

