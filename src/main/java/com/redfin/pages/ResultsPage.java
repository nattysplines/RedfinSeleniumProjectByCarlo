package com.redfin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    By allFiltersButton = By.xpath("//div[@id='WideSidepaneHeader--container']/div/form/div[5]/button/span[2]/span");
    By maxPriceInputField = By.xpath("(//input[@value=''])[3]");
    By twoPlusRoomsButton = By.cssSelector(".Beds .ItemPickerGroup__option:nth-child(4)");
    By twoBathroomsButton = By.cssSelector(".SearchFormSection__container > .ItemPickerGroup > .ItemPickerGroup__option--unselected:nth-child(4)");
    By doneButton = By.cssSelector(".applyButton > .ButtonLabel");
    By homeCardsLocator = By.cssSelector("div[id^='MapHomeCard']");
    By priceLocator = By.className("bp-Homecard__Price--value");
    By bedsLocator = By.className("bp-Homecard__Stats--beds");
    By bathsLocator = By.className("bp-Homecard__Stats--baths");

    public ResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openFilters() {
        WebElement filtersButton = wait.until(ExpectedConditions.elementToBeClickable(allFiltersButton));
        filtersButton.click();
    }

    public void setMaxPrice(int maxPrice) {
        WebElement maxPriceField = wait.until(ExpectedConditions.elementToBeClickable(maxPriceInputField));
        maxPriceField.click();
        maxPriceField.sendKeys(Integer.toString(maxPrice));
        maxPriceField.sendKeys(Keys.ENTER);
    }

    public void setBedrooms(int minBedrooms) {
        WebElement bedroomsOption = wait.until(ExpectedConditions.elementToBeClickable(twoPlusRoomsButton));
        bedroomsOption.click();
    }

    public void setBathrooms(int minBathrooms) {
        WebElement bathroomsOption = wait.until(ExpectedConditions.elementToBeClickable(twoBathroomsButton));
        bathroomsOption.click();
    }

    public void applyFilters() {
        WebElement doneBtn = wait.until(ExpectedConditions.elementToBeClickable(doneButton));
        doneBtn.click();
    }

    public List<WebElement> getHomeCards() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(homeCardsLocator));
    }
}
