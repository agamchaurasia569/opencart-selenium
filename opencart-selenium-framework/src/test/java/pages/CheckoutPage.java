package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.getTitle() != null && !driver.getTitle().isBlank();
    }
}
