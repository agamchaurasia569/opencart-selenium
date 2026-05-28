package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final By checkoutButton = By.linkText("Checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
