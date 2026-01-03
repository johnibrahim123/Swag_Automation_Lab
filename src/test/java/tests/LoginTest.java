package tests;

import org.testng.annotations.Test;
import pages.BaseTest;
import pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Navigate to login page
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        // Login with valid credentials from JSON
        String validUsername = DataDriven.jsonReader("validCredentials", "username");
        String validPassword = DataDriven.jsonReader("validCredentials", "password");

        loginPage.login(validUsername, validPassword);

        // Verify redirected to inventory page
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("/inventory.html") :
                "URL should contain '/inventory.html' but was: " + currentUrl;
    }

    @Test(description = "Verify invalid login shows correct error message")
    public void testInvalidLogin() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        // Login with invalid credentials from JSON
        String invalidUsername = DataDriven.jsonReader("invalidCredentials", "username");
        String invalidPassword = DataDriven.jsonReader("invalidCredentials", "password");

        loginPage.login(invalidUsername, invalidPassword);

        // Verify error message
        loginPage.verifyErrorMessage("Username and password do not match");
    }

    @Test(description = "Verify login without password shows correct error")
    public void testLoginWithoutPassword() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        // Enter valid username but no password
        String validUsername = DataDriven.jsonReader("validCredentials", "username");
        loginPage.enterUsername(validUsername);
        loginPage.clickLogin();

        // Verify error message
        loginPage.verifyErrorMessage("Password is required");
    }
}