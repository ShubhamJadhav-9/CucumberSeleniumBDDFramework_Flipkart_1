package com.automation.pages;

import com.automation.utils.DriverManager;
import com.automation.utils.LoggerUtil;
import com.automation.utils.PropertiesReader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipkartHomePage {
    public FlipkartHomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(name = "q")   // Stable Flipkart search input (name attribute)
    private WebElement searchInput;

    public void openFlipkart() {
        DriverManager.getDriver().get(PropertiesReader.getProperty("url"));
        LoggerUtil.getLogger().info("Opened Flipkart homepage");
    }

    public void searchFor(String product) {
        searchInput.clear();
        searchInput.sendKeys(product);
        searchInput.submit();   // Works perfectly on Flipkart (no separate button needed)
        LoggerUtil.getLogger().info("Searched for product: " + product);
    }
}