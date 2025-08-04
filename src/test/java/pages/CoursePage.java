package pages;

import org.openqa.selenium.*;
import java.util.List;
import java.util.Random;

public class CoursePage {
    WebDriver driver;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addRandomCourseToCart() {
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(
            "button.v-btn.v-theme--myCustomLightTheme.bg-primary.v-btn--density-default.rounded-xs.v-btn--size-default.v-btn--variant-flat.text-capitalize.w-100"));
        if (!addToCartButtons.isEmpty()) {
            int index = new Random().nextInt(addToCartButtons.size());
            addToCartButtons.get(index).click();
        }
    }

    public void goToCartAndCheckout() {
        driver.navigate().to("https://expertsacademy-staging.web.app/cart");
        driver.findElement(By.linkText("Checkout")).click();
    }
}
