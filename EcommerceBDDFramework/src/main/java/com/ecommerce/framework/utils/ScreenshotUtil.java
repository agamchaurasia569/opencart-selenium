package com.ecommerce.framework.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static void capture(WebDriver driver, String scenarioName) {
        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Paths.get("target", "screenshots", sanitize(scenarioName) + ".png");
            Files.createDirectories(destination.getParent());
            Files.copy(source.toPath(), destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Unable to capture screenshot", e);
        }
    }

    private static String sanitize(String text) {
        return text.replaceAll("[^a-zA-Z0-9-_\.]", "_");
    }
}
