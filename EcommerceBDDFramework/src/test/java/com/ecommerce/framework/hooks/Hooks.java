package com.ecommerce.framework.hooks;

import com.ecommerce.framework.driver.DriverFactory;
import com.ecommerce.framework.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(DriverFactory.getAppUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null && scenario.isFailed()) {
            ScreenshotUtil.capture(driver, scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
