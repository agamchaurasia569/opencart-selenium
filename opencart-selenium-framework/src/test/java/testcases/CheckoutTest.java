package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {
    @Test
    public void verifyCheckoutPageLoads() {
        Assert.assertTrue(new CheckoutPage(driver).isLoaded());
    }
}
