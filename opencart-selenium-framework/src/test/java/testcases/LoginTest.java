package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    public void validLoginTest() {
        driver.get(utilities.ConfigReader.get("baseUrl") + "index.php?route=account/login");
        new LoginPage(driver).login("demo@demo.com", "demo123");
    }
}
