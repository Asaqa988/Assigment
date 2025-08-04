package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillVisaPaymentDetails(String name, String number, String expiry, String cvc) {
        WebElement container = driver.findElement(By.xpath("//div[@class='v-expansion-panel-text__wrapper']"));
        container.findElements(By.tagName("input")).get(0).click();

        driver.findElement(By.cssSelector("#mysr-cc-name")).sendKeys(name);
        driver.findElement(By.cssSelector("#mysr-cc-number")).sendKeys(number);
        driver.findElement(By.cssSelector("input[placeholder='MM / YY']")).sendKeys(expiry);
        driver.findElement(By.cssSelector("input[placeholder='CVC']")).sendKeys(cvc);
        driver.findElement(By.cssSelector(".mysr-form-button")).click();
    }

    public void testAllAcsEmulatorOptions() throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auth_result")));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            String value = option.getAttribute("value");
            select.selectByValue(value);

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@type='submit']")));
            submitBtn.click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.urlContains("/my-purchases/payment-history/"));
            driver.navigate().back();

            dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auth_result")));
            select = new Select(dropdown);
        }
    }
}
