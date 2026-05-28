package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static String attachScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destDir = Paths.get("screenshots");
            Files.createDirectories(destDir);
            Path dest = destDir.resolve(name + "_" + System.currentTimeMillis() + ".png");
            Files.copy(src.toPath(), dest);
            return dest.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to save screenshot", e);
        }
    }
}
