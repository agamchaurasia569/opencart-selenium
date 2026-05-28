package com.ecommerce.framework.driver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver initDriver() {
        if (DRIVER.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.setImplicitWaitTimeout(Duration.ofSeconds(0));
            DRIVER.set(new ChromeDriver(options));
        }
        return DRIVER.get();
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    public static String getDefaultAppUrl() {
        Path path = Paths.get("src", "test", "resources", "webapp", "index.html").toAbsolutePath().normalize();
        return path.toUri().toString();
    }

    public static String getAppUrl() {
        return System.getProperty("app.url", getDefaultAppUrl());
    }
}
