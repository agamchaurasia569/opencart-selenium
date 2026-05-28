package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest {
    @Test
    public void registerNewUserTest() {
        driver.get(utilities.ConfigReader.get("baseUrl") + "index.php?route=account/register");
        new RegistrationPage(driver).register("Agam", "Chaurasia", "agam" + System.currentTimeMillis() + "@mail.com", "9999999999", "Password@123");
    }
}
