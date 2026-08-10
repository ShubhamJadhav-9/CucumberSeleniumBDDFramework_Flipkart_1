package com.automation.hooks;

import com.automation.utils.DriverManager;
import com.automation.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void setUp(Scenario scenario) {
        LoggerUtil.getLogger().info("Starting scenario: " + scenario.getName());
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
            LoggerUtil.getLogger().error("Scenario FAILED: " + scenario.getName());
        }
        DriverManager.quitDriver();
        LoggerUtil.getLogger().info("Scenario finished: " + scenario.getName());
    }
}