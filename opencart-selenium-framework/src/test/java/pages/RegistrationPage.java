package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;
    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");
    private final By agree = By.name("agree");
    private final By continueButton = By.xpath("//input[@value='Continue']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String fn, String ln, String em, String tel, String pass) {
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(email).sendKeys(em);
        driver.findElement(telephone).sendKeys(tel);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);
        driver.findElement(agree).click();
        driver.findElement(continueButton).click();
    }
}
