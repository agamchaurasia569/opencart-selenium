package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final WebDriver driver;
    private final By addToCart = By.xpath("//button[contains(@onclick,'cart.add')]");
    private final By cartLink = By.id("cart-total");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstResultToCart() {
        driver.findElement(addToCart).click();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}
