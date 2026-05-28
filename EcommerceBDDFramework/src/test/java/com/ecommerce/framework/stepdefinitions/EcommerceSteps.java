package com.ecommerce.framework.stepdefinitions;

import java.util.List;

import com.ecommerce.framework.driver.DriverFactory;
import com.ecommerce.framework.pages.DashboardPage;
import com.ecommerce.framework.pages.LoginPage;
import com.ecommerce.framework.utils.ExcelUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EcommerceSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("file:"),
                "Browser should open the local demo app by default.");
    }

    @When("User reads username and password from Excel row {int}")
    public void user_reads_username_and_password_from_excel_row(Integer rowNum) {
        String username = ExcelUtil.getUsername(rowNum);
        String password = ExcelUtil.getPassword(rowNum);
        loginPage.login(username, password);
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        Assert.assertTrue(dashboardPage.isLoggedIn(), "Login failed.");
    }

    @When("User searches for product {string}")
    public void user_searches_for_product(String productName) {
        dashboardPage.searchProduct(productName);
        Assert.assertTrue(dashboardPage.isProductVisible(productName),
                "Product should remain visible after search: " + productName);
    }

    @When("User applies filter {string}")
    public void user_applies_filter(String filterValue) {
        dashboardPage.applyFilter(filterValue);
    }

    @When("User adds multiple products to cart")
    public void user_adds_multiple_products_to_cart() {
        dashboardPage.addVisibleProductsToCart(2);
    }

    @When("User removes one product from cart")
    public void user_removes_one_product_from_cart() {
        dashboardPage.scrollToCartSection();
        int before = dashboardPage.getCartRowCount();
        dashboardPage.removeFirstProductFromCart();
        int after = dashboardPage.getCartRowCount();
        Assert.assertTrue(after == before - 1, "One item should be removed from the cart.");
    }

    @Then("User validates total amount in cart")
    public void user_validates_total_amount_in_cart() {
        List<Double> prices = dashboardPage.getCartPrices();
        double expected = prices.stream().mapToDouble(Double::doubleValue).sum();
        double actual = dashboardPage.getDisplayedTotalAmount();
        Assert.assertEquals(actual, expected, 0.01, "Cart total should match the sum of item prices.");
    }

    @Then("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        dashboardPage.proceedToCheckout();
        Assert.assertEquals(dashboardPage.getCheckoutStatus(), "Checkout completed successfully!",
                "Checkout confirmation message should be displayed.");
    }

    @Then("User logs out")
    public void user_logs_out() {
        dashboardPage.logout();
        Assert.assertFalse(dashboardPage.isLoggedIn(), "User should be logged out.");
    }
}
