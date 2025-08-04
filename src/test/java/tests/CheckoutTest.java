package tests;

import base.BaseTest;
import pages.*;

import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1)
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.openLoginPage();
        loginPage.handleCookie();
        loginPage.login("abdulraheemsaka2025@gmail.com", ";mK[hrb#");
        loginPage.enterOtp(new String[]{"1", "2", "3", "4"});
        loginPage.waitForRedirect();
    }

    @Test(priority = 2)
    public void testAddCourseToCart() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.addRandomCourseToCart();
        coursePage.goToCartAndCheckout();
    }

    @Test(priority = 3)
    public void testCheckoutWithVisa() throws InterruptedException {
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.fillVisaPaymentDetails("visa visa", "4242424242424242", "1229", "123");
        checkoutPage.testAllAcsEmulatorOptions();
    }
}
