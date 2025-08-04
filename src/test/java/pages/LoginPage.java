package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openLoginPage() {
        driver.get("https://expertsacademy-staging.web.app/auth/login");
    }

    public void handleCookie() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Accept All']/ancestor::button")));
            cookieBtn.click();
        } catch (TimeoutException ignored) {}
    }

    public void login(String email, String password) {
        List<WebElement> inputs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
            By.cssSelector("input.v-field__input"), 1));
        inputs.get(0).sendKeys(email);
        inputs.get(1).sendKeys(password);

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[.//span[text()='Continue']]")));
        continueBtn.click();
    }

    public void enterOtp(String[] otpDigits) {
        wait.until(ExpectedConditions.numberOfElementsToBe(
            By.cssSelector("input.v-otp-input__field"), otpDigits.length));
        List<WebElement> otpInputs = driver.findElements(By.cssSelector("input.v-otp-input__field"));
        for (int i = 0; i < otpDigits.length; i++) {
            otpInputs.get(i).sendKeys(otpDigits[i]);
        }

        try {
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Submit']]")));
            submitBtn.click();
        } catch (TimeoutException ignored) {}
    }

    public void waitForRedirect() {
        wait.until(ExpectedConditions.urlContains("/lms/all-courses"));
    }
}
