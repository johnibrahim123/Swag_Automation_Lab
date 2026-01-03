package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".inventory_item")
    private List<WebElement> productItems;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifies page title is "Swag Labs"
     */
    public void verifyPageTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs",
                "Page title should be 'Swag Labs' but was: " + actualTitle);
    }

    /**
     * Verifies cart icon is displayed
     */
    public void verifyCartIconDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(cartIcon));
        Assert.assertTrue(cartIcon.isDisplayed(),
                "Cart icon should be displayed");
    }

    /**
     * Verifies there are 6 products displayed
     */
    public void verifySixProductsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        Assert.assertEquals(productItems.size(), 6,
                "There should be 6 products displayed, but found: " + productItems.size());
    }
}