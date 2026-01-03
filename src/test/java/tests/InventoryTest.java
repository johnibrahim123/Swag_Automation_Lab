package tests;

import org.testng.annotations.Test;
import pages.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTest {

    @Test(description = "Verify inventory page elements after successful login")
    public void testInventoryPageElements() {
        // Navigate and login
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        String validUsername = DataDriven.jsonReader("validCredentials", "username");
        String validPassword = DataDriven.jsonReader("validCredentials", "password");
        loginPage.login(validUsername, validPassword);

        // Verify inventory page elements
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.verifyPageTitle();
        inventoryPage.verifyCartIconDisplayed();
        inventoryPage.verifySixProductsDisplayed();
    }
}