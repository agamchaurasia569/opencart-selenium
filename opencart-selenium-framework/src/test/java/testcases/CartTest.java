package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTest extends BaseTest {
    @Test
    public void checkoutFromCartTest() {
        new CartPage(driver).checkout();
    }
}
