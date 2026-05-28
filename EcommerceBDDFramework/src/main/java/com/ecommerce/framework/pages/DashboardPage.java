package com.ecommerce.framework.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    @FindBy(id = "loggedInUser")
    private WebElement loggedInUser;

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    @FindBy(id = "searchBtn")
    private WebElement searchButton;

    @FindBy(id = "filterDropdown")
    private WebElement filterDropdown;

    @FindBy(id = "checkoutBtn")
    private WebElement checkoutButton;

    @FindBy(id = "logoutBtn")
    private WebElement logoutButton;

    @FindBy(id = "checkoutStatus")
    private WebElement checkoutStatus;

    @FindBy(id = "cartTotal")
    private WebElement cartTotal;

    @FindBy(id = "cartTable")
    private WebElement cartTable;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoggedIn() {
        return isVisible(loggedInUser) && !textOf(loggedInUser).isBlank();
    }

    public void searchProduct(String productName) {
        type(searchBox, productName);
        click(searchButton);
    }

    public void applyFilter(String filterValue) {
        click(filterDropdown);
        filterDropdown.sendKeys(filterValue);
        filterDropdown.sendKeys(Keys.ENTER);
    }

    public void addProductToCart(String productName) {
        WebElement card = findProductCard(productName);
        WebElement addButton = card.findElement(By.cssSelector("button[data-action='add-to-cart']"));
        click(addButton);
    }

    public void addVisibleProductsToCart(int count) {
        List<WebElement> cards = visibleProductCards();
        int added = 0;
        for (WebElement card : cards) {
            if (added >= count) {
                break;
            }
            try {
                WebElement addButton = card.findElement(By.cssSelector("button[data-action='add-to-cart']"));
                click(addButton);
                added++;
            } catch (StaleElementReferenceException ignored) {
                // page is static enough; ignore and continue
            }
        }
        if (added < count) {
            throw new IllegalStateException("Only " + added + " visible products were found, but " + count + " were requested.");
        }
    }

    public void removeFirstProductFromCart() {
        WebElement removeButton = cartTable.findElement(By.cssSelector("tbody tr:first-child button[data-action='remove']"));
        click(removeButton);
    }

    public double getDisplayedTotalAmount() {
        String text = textOf(cartTotal).replace("Total: ", "").replace("$", "").trim();
        return Double.parseDouble(text);
    }

    public int getCartRowCount() {
        return cartTable.findElements(By.cssSelector("tbody tr")).size();
    }

    public List<Double> getCartPrices() {
        List<Double> prices = new ArrayList<>();
        List<WebElement> rows = cartTable.findElements(By.cssSelector("tbody tr"));
        for (WebElement row : rows) {
            String priceText = row.findElement(By.cssSelector("td[data-col='price']")).getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    public String getCheckoutStatus() {
        return textOf(checkoutStatus);
    }

    public void logout() {
        click(logoutButton);
    }

    public boolean isProductVisible(String productName) {
        List<WebElement> cards = visibleProductCards();
        for (WebElement card : cards) {
            String name = card.findElement(By.cssSelector("[data-col='name']")).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void scrollToCartSection() {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('cartSection').scrollIntoView({behavior:'instant', block:'start'});");
    }

    private WebElement findProductCard(String productName) {
        List<WebElement> cards = visibleProductCards();
        for (WebElement card : cards) {
            String name = card.findElement(By.cssSelector("[data-col='name']")).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Product not visible: " + productName);
    }

    private List<WebElement> visibleProductCards() {
        return driver.findElements(By.cssSelector(".product-card:not(.hidden)"));
    }
}
