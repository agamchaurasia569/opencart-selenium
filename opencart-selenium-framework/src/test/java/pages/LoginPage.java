package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By email = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginButton = By.xpath("//input[@value='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userEmail, String userPassword) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPassword);
        driver.findElement(loginButton).click();
    }
}
