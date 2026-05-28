package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

public class ProductSearchTest extends BaseTest {
    @Test
    public void searchAndAddProductTest() {
        HomePage home = new HomePage(driver);
        home.searchProduct("iPhone");
        new ProductPage(driver).addFirstResultToCart();
    }
}
