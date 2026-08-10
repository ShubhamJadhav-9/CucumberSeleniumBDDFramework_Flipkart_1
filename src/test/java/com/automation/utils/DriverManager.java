package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", PropertiesReader.getProperty("browser"));
            boolean useGrid = Boolean.parseBoolean(PropertiesReader.getProperty("grid"));

            if (useGrid) {
                try {
                    URL hubUrl = new URL(PropertiesReader.getProperty("hub.url"));
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setBrowserName(browser);
                    driver.set(new RemoteWebDriver(hubUrl, caps));
                } catch (Exception e) {
                    LoggerUtil.getLogger().error("Grid connection failed", e);
                }
            } else {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new FirefoxDriver());
                        break;
                    default:
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                }
            }

            // Common setup for all browsers/Grid + implicit wait from properties
            WebDriver drv = driver.get();
            drv.manage().window().maximize();
            int timeout = Integer.parseInt(PropertiesReader.getProperty("timeout"));
            drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}